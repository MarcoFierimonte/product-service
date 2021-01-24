package com.lastminute.store.product.model;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ProductTest {

//    @Nested
//    class CalculateSaleTaxesTest {
//
//        @Test
//        void whenProductTypeIsNotGenericThenReturnZero() {
//            // given
//            Product input = new Product(1, "Harry Potter", 212, ProductType.BOOK);
//
//            // when
//            Float actual = input.getSalesTaxes();
//
//            // then
//            assertThat(actual).isZero();
//        }
//
//        @Test
//        void whenProductTypeIsGenericThenReturnTaxes() {
//            // given
//            Product input = new Product(1, "Red Pencil", 212, ProductType.GENERIC);
//
//            // when
//            Float actual = input.getSalesTaxes();
//
//            // then
//            assertThat(actual).isNotZero();
//        }
//    }
//
//    @Nested
//    class CalculateGrossAmountTest {
//
//        @ParameterizedTest(name = "#{index} - Params: netPrice={0}, importDuty={1}, productType={2}, expectetResult={3}")
//        @MethodSource("com.lastminute.store.product.model.ProductTest#inputPrice")
//        void shouldComputeGrossAmount(Double netPrice, Float importDuty, ProductType productType, Double expectedResult) {
//            // given
//            Product input = new Product(1, "Harry Potter", 212, productType);
//            input.setNetPrice(netPrice);
//            input.setImportDuty(importDuty);
//
//            // when
//            Double actual = input.grossAmount();
//
//            // then
//            assertThat(actual).isEqualTo(expectedResult);
//        }
//
//
//    }

    static Stream<Arguments> inputPrice() {
        return Stream.of(
                // (netPrice, importDuty, productType, expectetResult)
                arguments(15.82, 0.05F, ProductType.BOOK, 16.611)
        );
    }



}

