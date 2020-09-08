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

    @Override
    public AbsInteger getSum(AbsInteger num1, AbsInteger num2) {
       byte c = Byte.valueOf(num1.toString());
       byte d = Byte.valueOf(num2.toString());
       byte e = (byte) (c+d);

       AbsInteger result = new ByteInteger(e);
       return result;
    }


}
