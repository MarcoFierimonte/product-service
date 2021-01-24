package com.lastminute.store.product.boot;

import com.lastminute.store.product.model.Decimal;
import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;
import com.lastminute.store.product.model.ProductType;
import com.lastminute.store.product.service.OrderService;
import com.lastminute.store.product.util.Utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Integer ID_POS = 0;
    private static final Integer TYPE_POS = 1;
    private static final Integer NAME_POS = 2;
    private static final Integer PRICE_POS = 3;
    private static final Integer IS_IMPORTED_POS = 4;
    private static final Integer QUANTITY_POS = 5;
    private static final Integer ORDER_ID_POS = 6;

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get("/home/marco/git/DEMO-PROJECTS/product-service/data/order1.csv");
        List<String> data = Utility.readFile(path.toFile());
        for (int n = 0; n < data.size(); n++) {
            if(n == 0) {
                // just the first line: expected comment line
                continue;
            }
            String line = data.get(n);
            // extract data from CSV
            String[] cells = line.split(",");
            String productId = cells[ID_POS].trim();
            ProductType productType = ProductType.valueOf(cells[TYPE_POS].trim());
            String name = cells[NAME_POS].trim();
            Decimal netPrice = Decimal.of(cells[PRICE_POS].trim());
            boolean isImported = Boolean.parseBoolean(cells[IS_IMPORTED_POS].trim());
            Integer quantity = Integer.parseInt(cells[QUANTITY_POS].trim());
            Integer orderId = Integer.parseInt(cells[ORDER_ID_POS].trim());
            // create Product objects
            Product product = new Product(productId, name, orderId, productType, netPrice, isImported);
            product.setQuantity(quantity);
            products.add(product);
        }

        // fullfit the order
        OrderService orderService = new OrderService();
        Order order = orderService.insertOrder(products);
        System.out.println(order);

    }
}
