package com.lastminute.store.product.service;

import com.lastminute.store.product.exception.NoReceiptNumberAvailableException;
import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;
import com.lastminute.store.product.service.api.BuildOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService implements BuildOrder {

    private static final List<Integer> availableReceipts = new ArrayList<>(50);

    public OrderService() {
        for (int i = 0; i < 50; i++) {
            availableReceipts.add(i + 1);
        }
    }

    @Override
    public Order build(List<Product> products) {
        Order order = new Order(new Date());
        order.add(products);
        // all products have the same order id
        Integer orderId = products.get(0)
                                  .getOrderId();
        order.setOrderId(orderId);
        order.setReceiptNumber(generateReceiptNumber());
        order.build();
        return order;
    }

    private Integer generateReceiptNumber() {
        if (availableReceipts.isEmpty()) {
            throw new NoReceiptNumberAvailableException("No more receipt numbers available");
        }
        return availableReceipts.remove(0);
    }

}
