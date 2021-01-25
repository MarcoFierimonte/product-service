package com.lastminute.store.product.boot;

import com.lastminute.store.product.model.Order;

public final class DataViewer {


    private DataViewer() {
        // no op
    }

    public static void view(Order order) {
        System.out.println("OUTPUT");
    }
}
