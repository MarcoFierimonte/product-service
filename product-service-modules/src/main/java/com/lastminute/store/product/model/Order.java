package com.lastminute.store.product.model;

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

    public Order(Integer orderId, Date date, Integer receiptNumber) {
        this.orderId = orderId;
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
        getProducts().add(product);
        return this;
    }

    public List<Product> getProducts() {
        if(products == null) {
            products = new ArrayList<Product>();
        }
        return products;
    }

    public Integer getOrderId() {
        return orderId;
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
