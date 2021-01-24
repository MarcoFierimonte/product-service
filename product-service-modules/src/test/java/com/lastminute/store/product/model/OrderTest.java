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
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id2", "Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);
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
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id1", "Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);

            // when
            order.addProduct(p1);
            Exception exception = assertThrows(
                    ProductAlreadyExistException.class,
                    () -> {
                        order.addProduct(p2);
                    });

            // then
            assertTrue(exception.getMessage()
                                .contains("id=[id1]"));
        }

        @Test
        void twoProductWithDifferentOrderIdshouldThrowAnException() {
            // given
            Order order = new Order(NOW, 99);
            order.setOrderId(212);
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id2", "Red Pencil", 111, ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);

            // when
            order.addProduct(p1);
            Exception exception = assertThrows(
                    InvalidProductException.class,
                    () -> {
                        order.addProduct(p2);
                    });

            // then
            assertTrue(exception.getMessage()
                                .contains("id=[id2]"));
        }

    }


}

