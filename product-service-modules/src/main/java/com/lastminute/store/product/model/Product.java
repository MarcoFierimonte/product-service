package com.lastminute.store.product.model;

public class Product {

    private static final float ROUNDING_RULE_VALUE = 0.05F;

    /**
     * The product identifier
     */
    private Integer productId;

    /**
     * The product name
     */
    private String name;

    /**
     * The product type
     */
    private ProductType productType;

    /**
     * The number of products belonging to the same order
     */
    private Integer quantity;

    /**
     * Single item net price
     */
    private Decimal netPrice;

    /**
     * Import duty value in cents.
     */
    private Tax importDuty;

    /**
     * Associated order id
     */
    private Integer orderId;

    public Product(String name, Integer orderId, ProductType productType, Decimal netPrice) {
        this.name = name;
        this.orderId = orderId;
        this.productType = productType;
        this.netPrice = netPrice;
        // default 0.05
        this.importDuty = Tax.of(0.05);
    }

    /**
     * Get the basic sales taxes for this product.
     *
     * @return the basic sales taxes
     */
    public Tax getSalesTaxes() {
        return Tax.of(productType.getTaxValue());
    }

    /**
     * Return the total taxaction value for the product round to the nearest 5 cents.
     *
     * @return the total taxaction value
     */
    public Tax totalTaxaction() {
        Decimal taxed = netPrice.multiply(importDuty.plus(getSalesTaxes()));
        Decimal rounded = taxed.roundToNearest(ROUNDING_RULE_VALUE);
        return Tax.of(rounded.multiply(Decimal.of(getQuantity())));
    }

    /**
     * Return the TOTAL NET amount for the product including its quantity.
     *
     * @return the gross amount
     */
    public Decimal totalNetAmount() {
        return netPrice.multiply(Decimal.of(getQuantity()));
    }

    /**
     * Return the TOTAL GROSS amount for the product including its quantity.
     *
     * @return the gross amount
     */
    public Decimal totalGrossAmount() {
        return totalNetAmount().plus(totalTaxaction());
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Integer getQuantity() {
        if (quantity == null) {
            quantity = 1;
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Decimal getNetPrice() {
        return netPrice;
    }

    public Tax getImportDuty() {
        return importDuty;
    }

    public void setImportDuty(Tax importDuty) {
        this.importDuty = importDuty;
    }

    public Integer getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return getProductId() != null ? getProductId().equals(product.getProductId()) : product.getProductId() == null;
    }

    @Override
    public int hashCode() {
        return getProductId() != null ? getProductId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" +
                productId +
                ", name='" +
                name +
                '\'' +
                ", productType=" +
                productType +
                ", quantity=" +
                quantity +
                ", netPrice=" +
                netPrice +
                ", importDuty=" +
                importDuty +
                ", orderId=" +
                orderId +
                '}';
    }
}
