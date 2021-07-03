import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {
        Calculator calculator = new Calculator();

        @Test
        @Tag("addition")
        public void testAddition() {
            double resultAddition = calculator.addition(1.2, 4.3);
            Assertions.assertEquals(5.5, resultAddition);
        }
        @Test
        @Tag("substraction")
        public void testSubstraction() {
            double resultSubstraction = calculator.subtraction(6, 5);
            Assertions.assertEquals(1, resultSubstraction);
        }

        @Tag("multiplication")
        @ParameterizedTest
        @ValueSource(doubles = { 10, 20, 30 })
        public void testMultiplication(double value) {
            Assertions.assertEquals(20, value);
    }


        @Tag("division")
        @RepeatedTest(2)
        public void testDivision(RepetitionInfo repetitionInfo) {
            double resultDivision = calculator.division(6, 4);
            Assertions.assertEquals(1.5, resultDivision);
            Assertions.assertEquals(2, repetitionInfo.getTotalRepetitions());
    }
    }

