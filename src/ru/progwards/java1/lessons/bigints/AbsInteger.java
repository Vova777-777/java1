package ru.progwards.java1.lessons.bigints;



public abstract class AbsInteger{

 static AbsInteger a;

    @Override
    public String toString() {
        String str = "" + a;
        return str;
    }

    public AbsInteger getSum(AbsInteger num1, AbsInteger num2) {
        int c = Integer.valueOf(num1.toString());
        int d = Integer.valueOf(num2.toString());
        int e = c+d;

        AbsInteger result = new IntInteger(e);
        return result;
    }

   public AbsInteger getNeedType(AbsInteger b){
       AbsInteger a1 = new ByteInteger((byte) 0);
       AbsInteger a2 = new ShortInteger((short) 0);
       AbsInteger a3 = new IntInteger( 0);
       if (Integer.valueOf(b.toString())>Byte.MIN_VALUE&&Integer.valueOf(b.toString())<Byte.MAX_VALUE) return a1;
       if (Integer.valueOf(b.toString())>Short.MIN_VALUE&&Integer.valueOf(b.toString())<Short.MAX_VALUE) return a2;
      else  return a3;
   }

    static AbsInteger add(AbsInteger num1, AbsInteger num2){
        AbsInteger forCall = new IntInteger( (byte)0);      // содал объект для вфзова методов;
        AbsInteger b = forCall.getSum(num1, num2);          //получил значение суммы num1 и num2;
         return forCall.getNeedType(b).getSum(num1, num2);  //вернул значение суммы в необходимом типе и заново посчиал
    }



    public static void main(String[] args) {
        System.out.println(add(new ByteInteger((byte) 127),new ByteInteger((byte)15)));
        System.out.println(add(new ByteInteger((byte) 127),new ByteInteger((byte)15)));
        System.out.println(add(new ShortInteger((short) 127),new ByteInteger((byte)10)));
        System.out.println(add(new IntInteger( 2000000000),new ByteInteger((byte) 5)));
        System.out.println((add(new ByteInteger((byte) 100),new ByteInteger((byte)15))).getClass());
        System.out.println((add(new IntInteger( 2000000000),new ByteInteger((byte) 5))).getClass());
        System.out.println((add(new ShortInteger((short) 127),new ByteInteger((byte)10))).getClass());

    }
}
