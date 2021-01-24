package com.lastminute.store.product.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Wrapper to the BigDecimal class with defined confoguration about
 * SCALE and RoundingMode.
 */
public class Decimal {

    private static final int SCALE = 8;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private final BigDecimal value;

    public static Decimal of(long value) {
        return new Decimal(BigDecimal.valueOf(value));
    }

    public static Decimal of(double value) {
        return new Decimal(BigDecimal.valueOf(value));
    }

    public static Decimal of(String value) {
        return new Decimal(new BigDecimal(value));
    }

    private Decimal(BigDecimal value) {
        this(value, SCALE);
    }

    public Decimal(BigDecimal value, int scale) {
        Objects.requireNonNull(value, "Decimal cannot be created from null value");
        this.value = value.setScale(scale, ROUNDING_MODE);
    }

    /**
     * Return this decimal rounded based on the param. Example param : "0.05")
     *
     * @param roundingValue the value that define the rounding rule
     * @return the rounded decimal
     */
    public Decimal roundToNearest(Float roundingValue) {
        BigDecimal roundingValueBD = BigDecimal.valueOf(roundingValue).setScale(2, ROUNDING_MODE);;
        return new Decimal(this.value.signum() == 0 ? value :
                (value.divide(roundingValueBD, 0, ROUNDING_MODE)).multiply(roundingValueBD));
    }

    public Decimal plus(Decimal other) {
        Objects.requireNonNull(other, "Cannot sum with a null Decimal");
        return new Decimal(value.add(other.value));
    }

    public Decimal divide(Decimal other) {
        Objects.requireNonNull(other, "Cannot divide with a null Decimal");
        return new Decimal(value.divide(other.value, ROUNDING_MODE));
    }

    public Decimal divide(Decimal other, RoundingMode roundingMode) {
        Objects.requireNonNull(other, "Cannot divide with a null Decimal");
        return new Decimal(value.divide(other.value, roundingMode));
    }

    public Decimal multiply(Decimal other) {
        Objects.requireNonNull(other, "Cannot multiply with a null Decimal");
        return new Decimal(value.multiply(other.value));
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Decimal decimal = (Decimal) o;

        return getValue().equals(decimal.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public String toString() {
        return "Decimal{" + "value=" + value +
                '}';
    }
}
