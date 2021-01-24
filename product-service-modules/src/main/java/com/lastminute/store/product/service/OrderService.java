package com.lastminute.store.product.service;

import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService {

    private static final List<Integer> availableReceipts = new ArrayList<>(50);

    public OrderService() {
        for (int i = 0; i < 50; i++) {
            availableReceipts.add(i + 1);
        }
    }

    public Order insertOrder(List<Product> products) {
        Order order = new Order(new Date());
        order.add(products);
        Integer orderId = products.get(0)
                                  .getOrderId();
        order.setOrderId(orderId);
        order.setReceiptNumber(availableReceipts.remove(0));
        order.build();
        return order;
    }


}
