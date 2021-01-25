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
        //
        List<Product> products = order.getProducts();
        LOG.info("INPUT");
        for (Product p : products) {
            LOG.info("{} {} at {}", p.getQuantity(), p.getName(), p.getNetPrice());
        }
        LOG.info("");
        LOG.info("OUTPUT");
        for (Product p : products) {
            LOG.info("{} {}: {}", p.getQuantity(), p.getName(), p.totalGrossAmount());
        }
        LOG.info("Sales Taxes: {}", order.getTotalTaxaction());
        LOG.info("Total: {}", order.getTotalPrice());
    }

}
