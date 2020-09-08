package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {

    private static ArrayInteger arrayInteger = new ArrayInteger(0);
    byte[] digits;
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

    boolean add(ArrayInteger num){
        boolean b = true;
        return b;
    }

    public static void main(String[] args) {

ArrayInteger a = new ArrayInteger(0);
        a.fromInt(new BigInteger("723"));

        System.out.println(Arrays.toString(arrayInteger.digits));
        System.out.println(arrayInteger.toInt().toString());

    }
}
