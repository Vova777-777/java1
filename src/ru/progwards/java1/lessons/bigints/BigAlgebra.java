package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class BigAlgebra {
//    в этом методе просто вместо умноженя числа самого на себя n раз
//    использовал квадрат числа умноженого на себя
//    не знаю насколько продуктивно и является ли это оптимизацией по заданию. Метод помечен другим названием

    BigDecimal fastPow1(BigDecimal num, int pow){
        BigDecimal num2 = new BigDecimal(String.valueOf(num.multiply(num)));
        BigDecimal numVspom = new BigDecimal(String.valueOf(num));
        int j = 1;
        for(int i =1; i < pow - j; i++) {
            num = num.multiply(num2);
            j++;
        }
        if (pow%2 != 0) return num;
        else num = num.multiply(numVspom);
        return num;
    }
//        в этом методе использовал схему "слева направо"
//        воспользовался операцией со строкой, тк посчитал наиболее простым решением
    BigDecimal fastPow(BigDecimal num, int pow){

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

    BigInteger fibonacci(int n){
        ArrayList<BigInteger> listFibo = new ArrayList<>();
        listFibo.add(new BigInteger("1"));
        listFibo.add(new BigInteger("1"));
        for (int i = 2; i <= n; i ++){
            listFibo.add(listFibo.get(i - 2).add(listFibo.get(i - 1)));
        }
        return listFibo.get(n - 1);
    }



    public static void main(String[] args) {
        BigAlgebra algebra = new BigAlgebra();
        BigDecimal proba = new BigDecimal("33");

        Date date1 = new Date();
        System.out.println(algebra.fastPow1(proba,45));
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
