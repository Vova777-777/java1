package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Finder {
    /*2.1 -  найти 2 соседних числа в коллекции сумма которых минимальна, вернуть коллекцию,
     содержащую индексы этих чисел*/
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
        Integer[] arrayForAdd = new Integer[numbers.size()];
        numbers.toArray(arrayForAdd);
        ArrayList<Integer> listForSum = new ArrayList<>();
        Integer sum = 0;
        for (int i = 0; i < arrayForAdd.length-1; i++){
            listForSum.add(arrayForAdd[i] + arrayForAdd[i+1]);
        }
        int valueMin = listForSum.get(0);
        for (int i = 0; i < listForSum.size()-1; i++){
            if (listForSum.get(i) < valueMin) valueMin = listForSum.get(i);
        }

        List<Integer> listForMinIndex = new ArrayList<>();
        for (int i = 0; i < listForSum.size(); i++){
            if (listForSum.get(i) == valueMin) {listForMinIndex.add(i); listForMinIndex.add(i+1);}
        }
        return  listForMinIndex;
    }



    public static void main(String[] args) {
        Collection<Integer> numbers = new ArrayList<>();
        numbers.add(23);
        numbers.add(11);
        numbers.add(2);
        numbers.add(1);
        numbers.add(2);
        System.out.println(findMinSumPair(numbers).toString());//задача 2.1
    }
}
