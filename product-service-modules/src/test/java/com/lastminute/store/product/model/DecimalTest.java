package com.lastminute.store.product.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DecimalTest {

    static Stream<Arguments> inputRoundTest() {
        return Stream.of(
                // (value, roundingRule, expectetResult)
                arguments(Decimal.of(15.821), 0.05F, Decimal.of(15.8)),
                arguments(Decimal.of(76.525), 0.05F, Decimal.of(76.55)),
                arguments(Decimal.of(76.25), 0.1F, Decimal.of(76.3)),
                arguments(Decimal.of(20.89), 0.05F, Decimal.of(20.9)),
                arguments(Decimal.of(14.993), 0.05F, Decimal.of(15))
        );
    }

    @Nested
    class RoundTest {

        @ParameterizedTest(name = "#{index} - Params: value={0}, roundingRule={1}, expectetResult={2}")
        @MethodSource("com.lastminute.store.product.model.DecimalTest#inputRoundTest")
        void shouldRoundToNearest(Decimal input, Float roundingRule, Decimal expectedResult) {
            // when
            Decimal actual = input.roundToNearest(roundingRule);

            // then
            assertThat(actual).isEqualTo(expectedResult);
        }

    }

    static Stream<Arguments> inputPlusTest() {
        return Stream.of(
                Arguments.of(Decimal.of(2.1), Decimal.of(4.05), Decimal.of(6.15)),
                Arguments.of(Decimal.of(-3.3), Decimal.of(3.3), Decimal.of(0)),
                Arguments.of(Decimal.of(-100.987), Decimal.of(-10), Decimal.of(-110.987))
        );
    }

    @Nested
    class PlusTest {

        @ParameterizedTest(name = "#{index} - Params: leftOperand={0}, rightOperand={1}, expectetResult={2}")
        @MethodSource("com.lastminute.store.product.model.DecimalTest#inputPlusTest")
        void shouldComputeTheSum(Decimal leftOperand, Decimal rightOperand, Decimal expectedResult) {
            // when
            Decimal actual = leftOperand.plus(rightOperand);

            // then
            assertThat(actual).isEqualTo(expectedResult);
        }

    }

}

