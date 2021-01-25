package com.lastminute.store.product.model;

import java.math.BigDecimal;

public class Tax extends Decimal {

    private static final int SCALE = 2;

    public static Tax of(long value) {
        return new Tax(BigDecimal.valueOf(value));
    }

    public static Tax of(double value) {
        return new Tax(BigDecimal.valueOf(value));
    }

    public static Tax of(String value) {
        return new Tax(new BigDecimal(value));
    }

    public static Tax of(Decimal decimal) {
        return new Tax(decimal.getValue());
    }

    private Tax(BigDecimal value) {
        super(value, SCALE);
    }

}
