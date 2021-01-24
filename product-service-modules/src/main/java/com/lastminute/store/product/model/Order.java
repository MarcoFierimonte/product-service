package com.lastminute.store.product.model;

import com.lastminute.store.product.exception.InvalidProductException;
import com.lastminute.store.product.exception.ProductAlreadyExistException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    /**
     * The order identifier
     */
    private Integer orderId;

    /**
     * The date of the order completition
     */
    private Date date;

    /**
     * The user receipt number
     */
    private Integer receiptNumber;

    /**
     * The products of the order
     */
    private List<Product> products;

    /**
     * The total gross price of this order
     */
    private Decimal totalPrice;

    /**
     * The order taxaction
     */
    private Decimal totalTaxaction;

    public Order(Date date) {
        this.date = date;
    }

    /**
     * Build the order with all information related to products.
     */
    public void build() {
        totalPrice = getProducts().stream()
                                  .map(Product::totalGrossAmount)
                                  .reduce(Decimal.of(0), Decimal::plus);
        totalTaxaction = getProducts().stream()
                                      .map(Product::totalTaxaction)
                                      .reduce(Decimal.of(0), Decimal::plus);
    }

    /**
     * Add several products.
     *
     * @param products the input products
     * @return the updated order
     */
    public Order add(List<Product> products) {
        for (Product p : products) {
            add(p);
        }
        return this;
    }

    /**
     * Add a new product to the current order.
     *
     * @param product the input product
     * @return the updated order
     */
    public Order add(Product product) {
        // each product in the order has unique ID
        if (getProducts().contains(product)) {
            throw new ProductAlreadyExistException("Product with id=[" + product.getProductId() + "] already present.");
        }
        // each product must belongs to the same order
        if (!getProducts().isEmpty()) {
            int productSize = getProducts().size();
            Product lastInsertProduct = getProducts().get(productSize - 1);
            if (!product.getOrderId()
                        .equals(lastInsertProduct.getOrderId())) {
                throw new InvalidProductException("Product with id=[" + product.getProductId() + "] belongs to a different order.");
            }
        }
        getProducts().add(product);
        return this;
    }

    /**
     * Remove a product to the current order.
     *
     * @param product the input product
     * @return the updated order
     */
    public Order removeProduct(Product product) {
        getProducts().remove(product);
        return this;
    }


    public List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
        }
        return products;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Decimal getTotalPrice() {
        return totalPrice;
    }

    public Decimal getTotalTaxaction() {
        return totalTaxaction;
    }

    public Date getDate() {
        return date;
    }

    public Integer getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Integer receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return getOrderId().equals(order.getOrderId());
    }

    @Override
    public int hashCode() {
        return getOrderId().hashCode();
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId +
                ", date=" + date +
                ", receiptNumber=" + receiptNumber +
                ", products=" + products +
                '}';
    }

}
