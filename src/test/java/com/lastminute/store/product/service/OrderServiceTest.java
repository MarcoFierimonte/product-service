package com.lastminute.store.product.service;

import com.lastminute.store.product.model.Decimal;
import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;
import com.lastminute.store.product.model.ProductType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    @Nested
    class InsertOrderTest {

        @Test
        void shouldFullfitTheOrder() {
            // given
            OrderService orderService = new OrderService();
            Integer orderIdentifier = 1;
            List<Product> products = new ArrayList<>();
            products.add(new Product("id1", "Harry Potter", orderIdentifier, ProductType.BOOK, Decimal.of(15), Boolean.FALSE));
            products.add(new Product("id2", "Apple", orderIdentifier, ProductType.FOOD, Decimal.of(2), Boolean.FALSE));
            products.add(new Product("id3", "Kiwi", orderIdentifier, ProductType.FOOD, Decimal.of(4), Boolean.TRUE));
            products.add(new Product("id4", "Table", orderIdentifier, ProductType.GENERIC, Decimal.of(124), Boolean.TRUE));

            // when
            Order actual = orderService.build(products);

            // then
            List<Product> actualProducts = actual.getProducts();
            assertThat(actualProducts).isNotEmpty();
            assertThat(actualProducts.size()).isEqualTo(4);
            assertThat(actual.getTotalPrice()).isEqualTo(Decimal.of(163.8));
            assertThat(actual.getTotalTaxaction()).isEqualTo(Decimal.of(18.8));
        }


    }


}

