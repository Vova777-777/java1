package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger {

    short a;

    ShortInteger(short a){
        this.a = a;
    }

    @Override
    public String toString() {
        String str = "" + a;
        return str;
    }


    @Override
    public AbsInteger getSum (AbsInteger num1, AbsInteger num2) {
        short c = Short.valueOf(num1.toString());
        short d = Short.valueOf(num2.toString());
        short e = (short) (c+d);

        AbsInteger result = new ShortInteger(e);
        return result;
    }
}
