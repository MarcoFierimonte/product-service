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

    private List<Product> products;

    public Order(Date date, Integer receiptNumber) {
        this.date = date;
        this.receiptNumber = receiptNumber;
    }

    /**
     * Add a new product to the current order.
     *
     * @param product the input product
     * @return the updated order
     */
    public Order addProduct(Product product) {
        // each product in the order has unique ID
        if (getProducts().contains(product)) {
            throw new ProductAlreadyExistException("Product with id=[" + product.getProductId() + "] already present.");
        }
        // each product must belongs to the same order
        if (!getProducts().isEmpty()) {
            int productSize = getProducts().size();
            Product lastInsertProduct = getProducts().get(productSize - 1);
            if(!product.getOrderId().equals(lastInsertProduct.getOrderId())) {
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

    public Date getDate() {
        return date;
    }

    public Integer getReceiptNumber() {
        return receiptNumber;
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
