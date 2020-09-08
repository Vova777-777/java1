package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger{
    int a;

    IntInteger(int a){
        this.a = a;
    }

    @Override
    public String toString() {
        String str = "" + a;
        return str;
    }

    @Override
    public AbsInteger getSum(AbsInteger num1, AbsInteger num2) {
        int c = Integer.valueOf(num1.toString());
        int d = Integer.valueOf(num2.toString());
        int e = c+d;

        AbsInteger result = new IntInteger(e);
        return result;
    }
}
