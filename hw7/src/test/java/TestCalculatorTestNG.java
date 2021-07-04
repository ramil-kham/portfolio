import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCalculatorTestNG {
        Calculator calculator = new Calculator();

        @Test(alwaysRun = true, description = "addition", priority = 0)
        public void testAddition() {
            double resultAddition = calculator.addition(1.2, 4.3);
            System.out.println("addition");
            Assert.assertEquals(resultAddition, 5.5);
        }
        @Test (dataProvider = "data-provider")
        public void testAddition1 (int a, int b, int result) {
            int sum = a + b;
            Assert.assertEquals(result, sum);
    }

        @Test(alwaysRun = true, description = "substraction", priority = 2)
        public void testSubstraction() {
            double resultSubstraction = calculator.subtraction(6, 5);
            System.out.println("substraction");
            Assert.assertEquals(resultSubstraction, 1);
        }

        @Test(alwaysRun = true, description = "multiplication", priority = 1, dataProvider = "dataProvider")
        public void testMultiplication(double value) {
            System.out.println("multiplication");
            Assert.assertEquals(value, 20);
    }
        @Test(alwaysRun = true, description = "division", invocationCount = 2, priority = 3)
        public void testDivision() {
            double resultDivision = calculator.division(6, 4);
            System.out.println("division");
            Assert.assertEquals(resultDivision, 1.5);
    }

        @DataProvider(name = "dataProvider")
        public Object[][] dataProvider() {
            return new Object[][] {{10}, {15}, {20}};
        }
        @DataProvider (name = "data-provider")
        public Object[][] dataProvider1(){
            return new Object[][] {{2, 5, 7}, {2, 7, 9}};
        }
    }

