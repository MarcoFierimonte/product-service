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
    class CalculateSaleTaxesTest {

        @Test
        void whenProductTypeIsNotGenericThenReturnZero() {
            // given
            Product input = new Product("Harry Potter", 212, ProductType.BOOK, Decimal.of(15));

            // when
            Tax actual = input.getSalesTaxes();

            // then
            assertThat(actual.getValue()).isZero();
        }

        @Test
        void whenProductTypeIsGenericThenReturnTaxes() {
            // given
            Product input = new Product("Red Pencil", 212, ProductType.GENERIC, Decimal.of(2));

            // when
            Tax actual = input.getSalesTaxes();

            // then
            assertThat(actual.getValue()).isNotZero();
        }
    }

    static Stream<Arguments> inputPrice() {
        return Stream.of(
                // (netPrice, productType, expectetResult)
                arguments(Decimal.of(15.82), ProductType.BOOK, Decimal.of(16.611))
        );
    }

    @Nested
    class CalculateGrossAmountTest {

        @ParameterizedTest(name = "#{index} - Params: netPrice={0}, productType={1}, expectetResult={2}")
        @MethodSource("com.lastminute.store.product.model.ProductTest#inputPrice")
        void shouldComputeGrossAmount(Decimal netPrice, ProductType productType, Decimal expectedResult) {
            // given
            Product input = new Product("Harry Potter", 212, productType, netPrice);

            // when
            Decimal actual = input.totalGrossAmount();

            // then
            assertThat(actual).isEqualTo(expectedResult);
        }


    }


}

