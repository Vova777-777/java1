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

}
