package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class BigAlgebra {
// ваш метод более наглядный, оставил свой, тк дошел до него сам в рамках обучения
//        в этом методе использовал схему "слева направо"
//        воспользовался операцией со строкой, тк посчитал наиболее простым решением
    static BigDecimal fastPow(BigDecimal num, int pow){

        BigDecimal result = new BigDecimal("1");
        BigDecimal numPowBit;
        int bit;
        String strPowBinary = Integer.toBinaryString(pow);
        int lengthStrPow = strPowBinary.length();
        for (int i = 0; i < lengthStrPow; i++){
            bit = Integer.valueOf(strPowBinary.substring(i,i+1));
            numPowBit = num.pow(bit);
            if (i == lengthStrPow - 1) {result = result.multiply(numPowBit); break;}
            result = result.multiply(numPowBit).pow(2);
        }

        return result;
    }

   static BigInteger fibonacci(int n){
        ArrayList<BigInteger> listFibo = new ArrayList<>();
        listFibo.add(new BigInteger("1"));
        listFibo.add(new BigInteger("1"));
        for (int i = 2; i <= n; i ++){
            listFibo.add(listFibo.get(i - 2).add(listFibo.get(i - 1)));
        }
        return listFibo.get(n - 1);
    }



    public static void main(String[] args) {

        System.out.println(fastPow(new BigDecimal("3"), 9));

        BigAlgebra algebra = new BigAlgebra();
        BigDecimal proba = new BigDecimal("33");

        Date date1 = new Date();

        Date date11 = new Date();
        System.out.println(date11.getTime() - date1.getTime());

        Date date2 = new Date();
        System.out.println(proba.pow(45));
        Date date22 = new Date();
        System.out.println(date22.getTime() - date2.getTime());

        Date date3 = new Date();
        System.out.println(algebra.fastPow(proba,45));
        Date date33 = new Date();
        System.out.println(date33.getTime() - date3.getTime());

        System.out.println("Число Фибо = " + algebra.fibonacci(7));
    }
}
