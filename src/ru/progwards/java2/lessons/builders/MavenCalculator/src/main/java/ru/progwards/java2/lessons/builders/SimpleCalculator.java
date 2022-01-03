package ru.progwards.java2.lessons.builders;
//1.1 Реализовать класс SimpleCalculator с методами
//int sum(int val1, int val2);
//int diff(int val1, int val2);
//int mult(int val1, int val2);
//int div(int val1, int val2);
//класс поместить в package ru.progwards.java2.lessons.tests.calc
//1.2 Создать параметризованные тесты для этих методов,
//тесты поместить в package ru.progwards.java2.lessons.tests.test.calc.
//1.3 Написать для всех методов тесты для проверки соответствующих исключений,
//тесты поместить в package ru.progwards.java2.lessons.tests.test.calc.

public class SimpleCalculator {
    public int sum(int val1, int val2) throws IntOverflowException{
        long sum = (long)val1 + val2;
        if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) throw new IntOverflowException(val1, val2, "Произошло переполнение");
        return (val1 + val2);
    }
    public int diff(int val1, int val2) throws IntOverflowException{
        long diff = (long)val1 - val2;
        if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) throw new IntOverflowException(val1, val2, "Произошло переполнение");
        return (val1 - val2);
    }

    public int mult(int val1, int val2) throws IntOverflowException{
        long mult = (long)val1 * val2;
        if (mult > Integer.MAX_VALUE || mult < Integer.MIN_VALUE) throw new IntOverflowException(val1, val2, "Произошло переполнение");
        return (val1 * val2);
    }

    public int div(int val1, int val2) throws ArithmeticException {
        if (val2 == 0) throw new ArithmeticException("Деление на ноль");
        return (val1 / val2);
    }
}
