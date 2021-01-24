package com.lastminute.store.product.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ProductTest {

    @Nested
    class CalculateBasicSaleTaxesTest {

        @Test
        void whenProductTypeIsNotGenericThenReturnZero() {
            // given
            Product input = new Product("id1", "Harry Potter", 212, ProductType.BOOK, Decimal.of(15), Boolean.FALSE);

            // when
            Tax actual = input.getBasicSalesTaxes();

            // then
            assertThat(actual.getValue()).isZero();
        }

        @Test
        void whenProductTypeIsGenericThenReturnTaxes() {
            // given
            Product input = new Product("id1", "Red Pencil", 212, ProductType.GENERIC, Decimal.of(2), Boolean.FALSE);

            // when
            Tax actual = input.getBasicSalesTaxes();

            // then
            assertThat(actual.getValue()).isNotZero();
        }

    }

    static Stream<Arguments> inputProductNotImported() {
        return Stream.of(
                // (netPrice, name, productType, expectetResult)
                arguments(Decimal.of(12.49), "Harry Potter", ProductType.BOOK, Decimal.of(12.49)),
                arguments(Decimal.of(14.99), "Pillow", ProductType.GENERIC, Decimal.of(16.49))
        );
    }

    static Stream<Arguments> inputProductImported() {
        return Stream.of(
                // (netPrice, name, productType, expectetResult)
                arguments(Decimal.of(10), "Box of chocolate", ProductType.FOOD, Decimal.of(10.5)),
                arguments(Decimal.of(47.50), "Perfume", ProductType.GENERIC, Decimal.of(54.65)),
                arguments(Decimal.of(21.36), "Aspirin", ProductType.MEDICAL, Decimal.of(22.41))
        );
    }

    @Nested
    class CalculateGrossAmountTest {

        @ParameterizedTest(name = "#{index} - Params: netPrice={0}, name={1}, productType={2}, expectetResult={3}")
        @MethodSource("com.lastminute.store.product.model.ProductTest#inputProductNotImported")
        void shouldComputeGrossAmountNotImported(Decimal netPrice, String name, ProductType productType, Decimal expectedResult) {
            // given
            Product input = new Product("id1", name, 212, productType, netPrice, Boolean.FALSE);

            // when
            Decimal actual = input.totalGrossAmount();

            // then
            assertThat(actual).isEqualTo(expectedResult);
        }

        @ParameterizedTest(name = "#{index} - Params: netPrice={0}, name={1}, productType={2}, expectetResult={3}")
        @MethodSource("com.lastminute.store.product.model.ProductTest#inputProductImported")
        void shouldComputeGrossAmountImported(Decimal netPrice,  String name, ProductType productType, Decimal expectedResult) {
            // given
            Product input = new Product("id1", name, 212, productType, netPrice, Boolean.TRUE);

            // when
            Decimal actual = input.totalGrossAmount();

            // then
            assertThat(actual).isEqualTo(expectedResult);
        }


    }


}

