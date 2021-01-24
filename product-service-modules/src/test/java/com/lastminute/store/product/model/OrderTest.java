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
            Order order = new Order(NOW);
            order.setOrderId(212);
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id2", "Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);
            // when
            order.add(p1)
                 .add(p2);

            // then
            List<Product> orderProducts = order.getProducts();
            assertThat(orderProducts).isNotEmpty();
            assertThat(orderProducts.size()).isEqualTo(2);
        }

        @Test
        void twoProductWithSameIdShouldThrowAnException() {
            // given
            Order order = new Order(NOW);
            order.setOrderId(212);
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id1", "Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);

            // when
            order.add(p1);
            Exception exception = assertThrows(
                    ProductAlreadyExistException.class,
                    () -> {
                        order.add(p2);
                    });

            // then
            assertTrue(exception.getMessage()
                                .contains("id=[id1]"));
        }

        @Test
        void twoProductWithDifferentOrderIdShouldThrowAnException() {
            // given
            Order order = new Order(NOW);
            order.setOrderId(212);
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id2", "Red Pencil", 111, ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);

            // when
            order.add(p1);
            Exception exception = assertThrows(
                    InvalidProductException.class,
                    () -> {
                        order.add(p2);
                    });

            // then
            assertTrue(exception.getMessage()
                                .contains("id=[id2]"));
        }

    }

    @Nested
    class RemoveProductFromOrderTest {

        @Test
        void shouldRemoveTheProduct() {
            // given
            Order order = new Order(NOW);
            order.setOrderId(212);
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id2", "Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);
            // when
            order.add(p1)
                 .add(p2);
            order.removeProduct(p1);
            // then
            List<Product> orderProducts = order.getProducts();
            assertThat(orderProducts.size()).isEqualTo(1);
            assertThat(orderProducts.get(0).getProductId()).isEqualTo("id2");
        }

    }

    @Nested
    class BuildOrderTest {

        @Test
        void shouldBuildTheOrder() {
            // given
            Order order = new Order(NOW);
            order.setOrderId(1);
            Product p1 = new Product("id1", "Harry Potter", order.getOrderId(), ProductType.BOOK, Decimal.of(15), Boolean.FALSE);
            Product p2 = new Product("id2", "Red Pencil", order.getOrderId(), ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);
            // when
            order.add(p1)
                 .add(p2);
            order.build();
            // then
            assertThat(order.getTotalPrice()).isEqualTo(Decimal.of(17.2));
            assertThat(order.getTotalTaxaction()).isEqualTo(Decimal.of(0.2));
        }

    }

}

