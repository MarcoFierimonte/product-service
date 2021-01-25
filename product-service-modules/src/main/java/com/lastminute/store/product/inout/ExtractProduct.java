package com.lastminute.store.product.inout;

import com.lastminute.store.product.exception.MalformedInputExceptionException;
import com.lastminute.store.product.inout.api.DataReader;
import com.lastminute.store.product.model.Decimal;
import com.lastminute.store.product.model.Product;
import com.lastminute.store.product.model.ProductType;

public class ExtractProduct implements DataReader<Product> {

    private static final Integer ID_POS = 0;
    private static final Integer TYPE_POS = 1;
    private static final Integer NAME_POS = 2;
    private static final Integer PRICE_POS = 3;
    private static final Integer IS_IMPORTED_POS = 4;
    private static final Integer QUANTITY_POS = 5;
    private static final Integer ORDER_ID_POS = 6;

    @Override
    public Product extract(String[] cells) {
        Product product;
        try {
            String productId = cells[ID_POS].trim();
            ProductType productType = ProductType.valueOf(cells[TYPE_POS].trim());
            String name = cells[NAME_POS].trim();
            Decimal netPrice = Decimal.of(cells[PRICE_POS].trim());
            boolean isImported = Boolean.parseBoolean(cells[IS_IMPORTED_POS].trim());
            Integer quantity = Integer.parseInt(cells[QUANTITY_POS].trim());
            Integer orderId = Integer.parseInt(cells[ORDER_ID_POS].trim());
            // create Product objects
            product = new Product(productId, name, orderId, productType, netPrice, isImported);
            product.setQuantity(quantity);
        } catch (Exception e) {
            throw new MalformedInputExceptionException("Error during parsing input data: [" + String.join(",", cells) + "]", e);
        }
        return product;
    }
}
