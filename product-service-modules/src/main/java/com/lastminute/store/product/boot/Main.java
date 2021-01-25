package com.lastminute.store.product.boot;

import com.lastminute.store.product.exception.MalformedInputExceptionException;
import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;
import com.lastminute.store.product.service.OrderService;
import com.lastminute.store.product.util.Utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get("/home/marco/git/DEMO-PROJECTS/product-service/data/order1.csv");
        List<String> data = Utility.readFile(path.toFile());
        for (int n = 0; n < data.size(); n++) {
            if(n == 0) {
                // jump the first line: expected comment line
                continue;
            }
            String line = data.get(n);
            // extract data from CSV
            String[] cells = line.split(",");
            if(cells.length != 7) {
                throw new MalformedInputExceptionException("Wrong column numbers for line=[" + (n+1) + "]. Expected 7, actual=[" + cells.length + "]");
            }
            Product product = DataReader.extractProduct(cells);
            products.add(product);
        }
        // fullfit the order
        OrderService orderService = new OrderService();
        Order order = orderService.insertOrder(products);
        // display output
        DataViewer.view(order);
    }

}
