package com.lastminute.store.product.model;

public enum ProductType {

    GENERIC(0, "GENERIC", 0.1F),
    BOOK(1, "BOOK", 0F),
    FOOD(2, "FOOD", 0F),
    MEDICAL(3, "MEDICAL", 0F)
    ;

    private Integer code;
    private String description;

    /**
     * The amount of basic taxaction for the product type
     */
    private Float taxValue;

    ProductType(Integer code, String description, Float taxValue) {
        this.code = code;
        this.description = description;
        this.taxValue = taxValue;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Float getTaxValue() {
        return taxValue;
    }
}
