package com.lastminute.store.product.model;

public enum ProductType {

    GENERIC(0, "GENERIC"),
    BOOK(1, "BOOK"),
    FOOD(2, "FOOD"),
    MEDICAL(3, "MEDICAL")
    ;

    private Integer code;
    private String description;

    ProductType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
