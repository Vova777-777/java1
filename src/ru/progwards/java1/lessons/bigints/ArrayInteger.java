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
          int lengthOfArray = arrayInteger.digits.length;
          String strNumberBigInt = "";

          for (int i = 0; i < lengthOfArray; i++){
              strNumberBigInt = strNumberBigInt + arrayInteger.digits[i];
          }
          return new BigInteger(strNumberBigInt);
       }

    boolean add(ArrayInteger num) {
        boolean forReturn = false;
        byte[] array = this.digits;
        byte[] numArray = num.digits;
        int leastLength = 0;
        if (array.length <= numArray.length) leastLength = array.length;
        else leastLength = numArray.length;
        if (numArray.length > array.length) return false;
        byte vspom = 0;
        for (int i = 0; i < leastLength; i++) {
            array[array.length - 1 - i] = (byte) (array[array.length - 1 - i] + numArray[numArray.length - 1 - i]);
            if (array[array.length - 1 - i] >= 10 && (array.length - 1 - i) != 0) {
                vspom = (byte) (array[array.length - 1 - i] - 10);
                array[array.length - 1 - i] = 0;
                array[array.length - 2 - i] = (byte) (array[array.length - 2 - i] + vspom);
            } else if (array[array.length - 1 - i] >= 10 && (array.length - 1 - i) == 0) {
                array[array.length - 1 - i] = 0;
                forReturn = false;
            } else forReturn = true;
        }
        return forReturn;
    }



    public static void main(String[] args) {

ArrayInteger a = new ArrayInteger(0);
ArrayInteger b = new ArrayInteger(1);
        a.fromInt(new BigInteger("723"));
        b.fromInt(new BigInteger("234"));

        System.out.println(Arrays.toString(a.digits));
        System.out.println(Arrays.toString(b.digits));
        System.out.println(a.toInt().toString());
        System.out.println(a.add(b));



    }
}
