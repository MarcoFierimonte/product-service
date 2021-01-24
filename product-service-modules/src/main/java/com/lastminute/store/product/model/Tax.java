package com.lastminute.store.product.model;

import java.math.BigDecimal;

public class Tax extends Decimal {

    private static final int SCALE = 2;

    public Tax(BigDecimal value) {
        super(value, SCALE);
    }

}
