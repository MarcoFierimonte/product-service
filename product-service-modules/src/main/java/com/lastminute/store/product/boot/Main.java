package com.lastminute.store.product.boot;

import com.lastminute.store.product.exception.MalformedInputExceptionException;
import com.lastminute.store.product.inout.ExtractProduct;
import com.lastminute.store.product.inout.ViewOrder;
import com.lastminute.store.product.inout.api.DataReader;
import com.lastminute.store.product.inout.api.DataViewer;
import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;
import com.lastminute.store.product.service.OrderService;
import com.lastminute.store.product.service.api.BuildOrder;
import com.lastminute.store.product.util.Utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static DataReader<Product> extractProduct = new ExtractProduct();
    private static DataViewer<Order> dataViewer = new ViewOrder();

    public static void main(String[] args) {
        argumentsValidator(args);
        String filePath = args[0].split("=")[1];
        List<Product> products = new ArrayList<>();
        Path path = Paths.get(filePath);
        List<String> data = Utility.readFile(path.toFile());
        for (int n = 0; n < data.size(); n++) {
            if(n == 0) {
                // jump the first line: expected comment line
                continue;
            }
            String line = data.get(n);
            Product product = extractProduct.extract(line);
            products.add(product);
        }
        // fullfit the order
        BuildOrder orderService = new OrderService();
        Order order = orderService.build(products);
        // display output
        dataViewer.view(order);
    }

    private static void argumentsValidator(String[] args) {
        if(args.length == 0) {
            throw new MalformedInputExceptionException("Needed at least one argument: [filePath]");
        }
        if(!args[0].split("=")[0].equals("filePath") ) {
            throw new MalformedInputExceptionException("Missing mandatory argument: [filePath]. Current arguments=[" + String.join(" ", args) + "]");
        }
    }

}
