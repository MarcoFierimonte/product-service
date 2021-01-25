package com.lastminute.store.product.model;

public class Product {

    /**
     * The product identifier, like a bar code
     */
    private String productId;

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
     * The product is imported or not
     */
    private boolean isImported;

    /**
     * Import duty value in cents.
     */
    private Tax importDuty;

    /**
     * Associated order id
     */
    private Integer orderId;

    public Product(String productId, String name, Integer orderId, ProductType productType, Decimal netPrice, boolean isImported) {
        this.productId = productId;
        this.name = name;
        this.orderId = orderId;
        this.productType = productType;
        this.netPrice = netPrice;
        this.isImported = isImported;
        // default 0.05
        if(isImported) {
            this.importDuty = Tax.of(0.05);
        } else {
            this.importDuty = Tax.of(0);
        }
    }

    /**
     * Get the basic sales taxes for this product.
     *
     * @return the basic sales taxes
     */
    public Tax getBasicSalesTaxes() {
        return Tax.of(productType.getTaxValue());
    }

    /**
     * Return the total taxaction amount for the product round to the nearest 5 cents.
     *
     * @return the total taxaction value
     */
    public Decimal totalTaxaction() {
        Decimal taxed = netPrice.multiply(importDuty.plus(getBasicSalesTaxes()));
        Decimal rounded = taxed.roundToNearest5Cent();
        return rounded.multiply(Decimal.of(getQuantity()));
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

    public String getProductId() {
        return productId;
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

    public boolean isImported() {
        return isImported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
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
                ", saleTaxes=" +
                totalTaxaction() +
                ", importDuty=" +
                importDuty +
                ", orderId=" +
                orderId +
                '}';
    }
}
