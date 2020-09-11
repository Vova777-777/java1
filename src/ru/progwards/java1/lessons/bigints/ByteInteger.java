package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger {

    byte a;

    ByteInteger(byte a){
        this.a = a;
    }



    @Override
    public String toString() {
        String str = "" + a;
        return str;
    }
}
