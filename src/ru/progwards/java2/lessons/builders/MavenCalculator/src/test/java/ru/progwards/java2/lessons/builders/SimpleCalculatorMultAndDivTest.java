package ru.progwards.java2.lessons.builders;


import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;


@RunWith(Enclosed.class)
public class SimpleCalculatorMultAndDivTest {
    static SimpleCalculator calculator;
    @BeforeClass
    public static void beforeClass(){
        calculator = new SimpleCalculator();
    }

    @RunWith(Parameterized.class)
    public static class MmultSimpleCalculator{
        int result;
        int val1;
        int val2;

        public MmultSimpleCalculator(int result, int val1, int val2) {
            this.result = result;
            this.val1 = val1;
            this.val2 = val2;
        }

        @Parameterized.Parameters(name = "TestMult {index}: {0} = {1} * {2}")
        public static Iterable<Object[]> data(){
            return Arrays.asList(new Object[][]{
                {3, 1, 3},
                {4, 2, 2},
                {10, 5, 2},
                {0, 0, 1000}
            });
        }

        @Test
        public void mult(){
            Assert.assertEquals(result, calculator.mult(val1, val2));
        }
    }

    public static class MultException{
        @Rule
        public ExpectedException exception = ExpectedException.none();
        @Test
        public void checkException(){
            exception.expect(IntOverflowException.class);
            exception.expectMessage("Произошло переполнение");
            calculator.mult(Integer.MAX_VALUE, 2);
            calculator.mult(Integer.MIN_VALUE, 2);
        }
    }

    @RunWith(Parameterized.class)
    public static class DivSimpleCalculator{
        int result;
        int val1;
        int val2;

        public DivSimpleCalculator(int result, int val1, int val2) {
            this.result = result;
            this.val1 = val1;
            this.val2 = val2;
        }

        @Parameterized.Parameters(name = "TestDiv {index}: {0} = {1} / {2}")
        public static Iterable<Object[]> dataForTest(){
            return Arrays.asList(new Object[][]{
                    {2, 10, 5},
                    {20, 100, 5},
                    {30, 150, 5},
                    {2, 14, 7}
            });
        }

        @Test
        public void div(){
            Assert.assertEquals(result, calculator.div(val1, val2));
        }

    }

    public static class DivException{
        @Rule
        public ExpectedException exception = ExpectedException.none();
        @Test
        public void checkDivException(){
            exception.expect(ArithmeticException.class);
            exception.expectMessage("Деление на ноль");
            calculator.div(100, 0);
        }
    }

    @AfterClass
    public static void afterClass(){
        calculator = null;
    }
}
