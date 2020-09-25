package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Creator {
    /* 1.1 - создать коллекцию и заполнить последовательностью четных возрастающих чисел начиная с 2
    количество элементов в коллекции n
    */
    public static Collection<Integer> fillEven(int n){
        ArrayList<Integer> listOfvenInteger = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < n; i++){
            num = num + 2;
            listOfvenInteger.add(num);
        }
            return listOfvenInteger;
    }

/* 1.2создать коллекцию и заполнить последовательностью нечетных убывающих чисел, минимальное число в коллекции 1,
 количество элементов в коллекции n */

    public static Collection<Integer> fillOdd(int n){
        List<Integer> listOddInteger = new ArrayList<>();
        int num = -1;
        for (int i = 0; i < n; i++){
            listOddInteger.add(num = num + 2);
        }
        Collections.sort(listOddInteger, Collections.reverseOrder());
        return listOddInteger;
    }

    /*создать коллекцию и заполнить ее тройками чисел. Каждая тройка создается по алгоритму:
     первое число тройки - индекс числа в коллекции, второе - индекс в квадрате, третье - индекс в кубе,
      количество элементов в коллекции n*3*/

    public static Collection<Integer> fill3(int n){
        List<Integer> listOddInteger = new ArrayList<>();
        for (int i = 0; i < n; i++){
            listOddInteger.add(i);
            listOddInteger.add(i*i);
            listOddInteger.add(i*i*i);
        }
        return listOddInteger;
    }


    public static void main(String[] args) {
        System.out.println(fillEven(7).toString());//задача 1.1;
        System.out.println(fillOdd(10).toString());//задача 1.2;
        System.out.println(fill3(10).toString());//задача 1.2;
        System.out.println(fill3(10).size());
    }

}
