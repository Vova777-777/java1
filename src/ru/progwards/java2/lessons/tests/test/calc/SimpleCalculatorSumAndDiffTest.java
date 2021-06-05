package ru.progwards.java2.lessons.tests.test.calc;


import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.calc.IntOverflowException;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import java.util.Arrays;

@RunWith(Enclosed.class)
public class SimpleCalculatorSumAndDiffTest {
    static SimpleCalculator calc;
    @BeforeClass
    public static void createCalc(){
        calc = new SimpleCalculator();
    }

    @RunWith(Parameterized.class)
    public static class SumSimpleCalculatorTest {
       public int sum;
       public int val1;
       public int val2;

        public SumSimpleCalculatorTest(int sum, int val1, int val2) {
            this.sum = sum;
            this.val1 = val1;
            this.val2 = val2;
        }

        @Parameterized.Parameters(name = "Test {index}: {0} = ({1}) + ({2})")
        public static Iterable<Object[]> data(){
            return Arrays.asList(new Object[][]{
                    {5, 2, 3},
                    {3, 2, 1},
                    {4, 2, 2},
                    {10, 5, 5}
            });
        }
        @Test
        public void sum(){
            Assert.assertEquals(sum, calc.sum(val1, val2));
        }


    }

    public static class SumExceptionSimpleCalculatorTest {
        @Test(expected = IntOverflowException.class)
        public void sumException(){
           calc.sum(Integer.MAX_VALUE, 1);
           calc.sum(Integer.MIN_VALUE, -1);
           calc.sum(Integer.MAX_VALUE, 10);
        }
    }

    @RunWith(Parameterized.class)
    public static class DiffSimpleCalculatorTest {
        int diff;
        int val1;
        int val2;


        public DiffSimpleCalculatorTest(int diff, int val1, int val2) {
            this.diff = diff;
            this.val1 = val1;
            this.val2 = val2;
        }

        @Parameterized.Parameters(name = "ТестDiff{index}: {0} = {1} - {2}")
        public static Iterable<Object[]> dataDiff() {
            return Arrays.asList(new Object[][]{
                    {5, 10, 5},
                    {2, 10, 8},
                    {150, 1000, 850},
                    {0, 6, 6}
            });
        }


        @Test
        public void diff() {
            System.out.println(calc);
            Assert.assertEquals(diff, calc.diff(val1, val2));
        }
    }


    public static class DiffExceptionSimpleCalculatorTest{
        @Rule
        public ExpectedException expectedException = ExpectedException.none();
        @Test
        public void diffException(){
            expectedException.expect(IntOverflowException.class);
            expectedException.expectMessage("Произошло переполнение");
            calc.diff(Integer.MIN_VALUE, 1);
            calc.diff(Integer.MAX_VALUE, -1);
        }



    }

    @AfterClass
    public static void destroyCalc(){
        calc = null;
    }
}