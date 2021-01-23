package com.lastminute.store.product.model;

public class Product {

    private static final double ROUNDING_RULE_VALUE = 0.05;

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
     * Total net price
     */
    private Double netPrice;

    /**
     * Sales taxes in cents
     */
    private Float salesTaxes;

    /**
     * Import duty value in cents
     */
    private Float importDuty;

    /**
     * Associated order id
     */
    private Integer orderId;

    public Product(Integer productId, String name, Integer orderId, ProductType productType) {
        this.productId = productId;
        this.name = name;
        this.orderId = orderId;
        this.productType = productType;
    }

    /**
     * Get the basic sales taxes for this product.
     *
     * @return the basic sales taxes
     */
    public Float getSalesTaxes() {
        switch (productType) {
            case GENERIC:
                return 0.1F;
            case BOOK:
            case FOOD:
            case MEDICAL:
            default:
                return 0F;
        }
    }

    /**
     * Return the total taxaction value for the products rounded up to the nearest 0.05.
     *
     * @return the total taxaction value
     */
    public Double totalTaxaction() {
        float saleTaxes = importDuty + getSalesTaxes();
        return Math.round(saleTaxes / ROUNDING_RULE_VALUE) * ROUNDING_RULE_VALUE;
    }

    /**
     * Return the TOTAL gross amount for the product including its quantity.
     *
     * @return the gross amount
     */
    public Double grossAmount() {
        return (netPrice + (netPrice * totalTaxaction())) * getQuantity();
    }

    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }


    public void setSalesTaxes(Float salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public Float getImportDuty() {
        return importDuty;
    }

    public void setImportDuty(Float importDuty) {
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

        return getProductId().equals(product.getProductId());
    }

    @Override
    public int hashCode() {
        return getProductId().hashCode();
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId +
                ", name='" + name + '\'' +
                ", productType=" + productType +
                ", quantity=" + quantity +
                ", netPrice=" + netPrice +
                ", salesTaxes=" + salesTaxes +
                ", importDuty=" + importDuty +
                ", orderId=" + orderId +
                '}';
    }
}
