package com.lastminute.store.product.service.api;

import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;

import java.util.List;

public interface BuildOrder {

    /**
     * Given the input products build the fullfit order.
     *
     * @param products the input products
     * @return the built order
     */
    Order build(List<Product> products);

}
