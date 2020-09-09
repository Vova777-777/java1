package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {

    private static ArrayInteger arrayInteger = new ArrayInteger(0);
    byte[] digits = new  byte[0];
    ArrayInteger(int n){
        byte[] digits = new byte[n];
        this.digits = digits;
    }

    void fromInt(BigInteger value){
        value.toString();
        int lengthOfBigInt = value.toString().length();
         arrayInteger = new ArrayInteger(lengthOfBigInt);

        for (int i = 0; i < lengthOfBigInt; i++){
            arrayInteger.digits[i] =Byte.parseByte(value.toString().substring(i,i+1));
            this.digits = arrayInteger.digits;
        }
   }
       BigInteger toInt(){
          int lengthOfArray = this.digits.length;
          String strNumberBigInt = "";

          for (int i = 0; i < lengthOfArray; i++){
              strNumberBigInt = strNumberBigInt + this.digits[i];
          }
          return new BigInteger(strNumberBigInt);
       }

    boolean add(ArrayInteger num) {

        boolean forReturn = false;
        byte[] array = this.digits;     //для более короткой записи в теле метода
        byte[] numArray = num.digits;   //для более короткой записи в теле метода
        int leastLength = 0;//


        if (array.length <= numArray.length) leastLength = array.length;

        else leastLength = numArray.length;

        if (numArray.length > array.length) return false;// вызывет переполнение

        byte additionalVariable = 0;  //переменная для работы с остатком при суммировании столбиком через цикл
        for (int i = 0; i < leastLength; i++) {
            array[array.length - 1 - i] = (byte) (array[array.length - 1 - i] + numArray[numArray.length - 1 - i]);
            if (array[array.length - 1 - i] >= 10 && (array.length - 1 - i) != 0) {
                additionalVariable = (byte) (array[array.length - 1 - i] - 10);
                array[array.length - 1 - i] = additionalVariable;
                array[array.length - 2 - i] = (byte) (array[array.length - 2 - i] + 1);
            } else if (array[array.length - 1 - i] >= 10 && (array.length - 1 - i) == 0) {
                array[array.length - 1 - i] = 0;
                forReturn = false;
            } else forReturn = true;
        }

        return forReturn;
    }



    public static void main(String[] args) {



        ArrayInteger ai1 = new ArrayInteger(8);
        ai1.fromInt(new BigInteger("18334797"));
        ArrayInteger ai2 = new ArrayInteger(5);
        ai2.fromInt(new BigInteger("11893"));
        ai1.add(ai2);
        System.out.println(ai1.toInt());//18346690
    }
}
