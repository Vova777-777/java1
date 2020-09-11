package ru.progwards.java1.lessons.bigints;



public abstract class AbsInteger{

 static AbsInteger a;

    @Override
    public String toString() {
        String str = "" + a;
        return str;
    }



   /* static AbsInteger add(AbsInteger num1, AbsInteger num2){
        int num11 = Integer.valueOf(num1.toString());
        int num22 = Integer.valueOf(num2.toString());
        int sum = num11 + num22;
        AbsInteger result;
        if(sum > Byte.MIN_VALUE && sum <Byte.MAX_VALUE) {result = new ByteInteger((byte) sum); return result;}
        else if(sum > Short.MIN_VALUE && sum < Short.MAX_VALUE) {result = new ShortInteger((short) sum); return result;}
        else result = new IntInteger(sum); return result;
    }
    */

    static AbsInteger add(AbsInteger num1, AbsInteger num2){
        int num11 = Integer.valueOf(num1.toString());
        int num22 = Integer.valueOf(num2.toString());
        int sum = num11 + num22;
        AbsInteger result = new IntInteger(0);
        result = result.getNeedType(sum);
        return result;

    }

    public AbsInteger getNeedType (int number){
        AbsInteger result;
        if(number > Byte.MIN_VALUE && number <Byte.MAX_VALUE) {result = new ByteInteger((byte) number); return result;}
        else if(number > Short.MIN_VALUE && number < Short.MAX_VALUE) {result = new ShortInteger((short) number); return result;}
        else result = new IntInteger(number); return result;
    }



    public static void main(String[] args) {
        System.out.println(add(new ByteInteger((byte) 13),new ByteInteger((byte)13)).getClass());

        System.out.println(add(new ByteInteger((byte) 127),new ByteInteger((byte)15)));
        System.out.println(add(new ShortInteger((short) 127),new ByteInteger((byte)10)));
        System.out.println(add(new IntInteger( 2000000000),new ByteInteger((byte) 5)));
        System.out.println((add(new ByteInteger((byte) 100),new ByteInteger((byte)15))).getClass());
        System.out.println((add(new IntInteger( 2000000000),new ByteInteger((byte) 5))).getClass());
        System.out.println((add(new ShortInteger((short) 127),new ByteInteger((byte)10))).getClass());

    }
}
