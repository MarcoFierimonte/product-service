package com.lastminute.store.product.inout;

import com.lastminute.store.product.inout.api.DataViewer;
import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ViewOrder implements DataViewer<Order> {

    private static final Logger LOG = LoggerFactory.getLogger(ViewOrder.class);

    @Override
    public void view(Order order) {
        LOG.info("OUTPUT");
        List<Product> products = order.getProducts();
        for (Product p : products) {
            LOG.info("{} {}: {}", p.getQuantity(), p.getName(), p.totalGrossAmount());
        }
        LOG.info("Sales Taxes: {}", order.getTotalTaxaction());
        LOG.info("Total: {}", order.getTotalPrice());
    }

}
