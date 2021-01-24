package com.lastminute.store.product.model;

import com.lastminute.store.product.exception.InvalidProductException;
import com.lastminute.store.product.exception.ProductAlreadyExistException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {

    private static final Date NOW = new Date();

    @Nested
    class CreateOrderTest {

        @Test
        void shouldContainsAllProducts() {
            // given
            Order order = new Order(NOW, 99);
            order.setOrderId(212);
            Product p1 = new Product("Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15));
            p1.setProductId(1);
            Product p2 = new Product("Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2));
            p1.setProductId(2);
            // when
            order.addProduct(p1)
                 .addProduct(p2);

            // then
            List<Product> orderProducts = order.getProducts();
            assertThat(orderProducts).isNotEmpty();
            assertThat(orderProducts.size()).isEqualTo(2);
        }

        @Test
        void twoProductWithSameIdshouldThrowAnException() {
            // given
            Order order = new Order(NOW, 99);
            order.setOrderId(212);
            Product p1 = new Product("Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15));
            p1.setProductId(1);
            Product p2 = new Product("Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2));
            p2.setProductId(1);

            // when
            order.addProduct(p1);
            Exception exception = assertThrows(
                    ProductAlreadyExistException.class,
                    () -> {
                        order.addProduct(p2);
                    });

            // then
            assertTrue(exception.getMessage().contains("id=[1]"));
        }

        @Test
        void twoProductWithDifferentOrderIdshouldThrowAnException() {
            // given
            Order order = new Order(NOW, 99);
            order.setOrderId(212);
            Product p1 = new Product("Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15));
            p1.setProductId(1);
            Product p2 = new Product("Red Pencil", 111, ProductType.GENERIC, Decimal.of(2));
            p2.setProductId(2);

            // when
            order.addProduct(p1);
            Exception exception = assertThrows(
                    InvalidProductException.class,
                    () -> {
                        order.addProduct(p2);
                    });

            // then
            assertTrue(exception.getMessage().contains("id=[2]"));
        }

    }


}

