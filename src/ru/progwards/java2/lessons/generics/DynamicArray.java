package ru.progwards.java2.lessons.generics;

import com.google.inject.internal.cglib.core.$ProcessArrayCallback;
import com.google.inject.internal.cglib.proxy.$Dispatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DynamicArray<T> {
//Реализовать класс по типу BlockArray - обобщающий динамический массив, растущий блоками, на основе обычного
// статического массива. Стратегия роста - при полном заполнении текущего объема, новый размер массива должен
// быть в 2 раза больше предыдущего.
//3.1 в классе разместить private переменную - массив обобщающего типа.
//3.2 конструктор - по умолчанию.
//3.2 метод add - добавляет элемент в конец массива.
//3.3 метод с именем insert - добавляет элемент в заданную позицию позицию массива. Параметр int pos - первый,
// параметр с элементом массива - второй.
//3.4 метод remove(int pos) - удаляет элемент в позиции pos массива.
//3.5 метод с get(int pos) - возвращает элемент по индексу pos.
//3.6 метод с size() - возвращает текущий реальный объем массива.

    private T[] array = (T[]) new Object[1];

    public void add(T t){
        if (isEndDynamicArray()) array = addBlockWithUseOtherMethod(array);
        array[findLastIndexValueDynamicArray()] = t;
    }

    public void insert(int pos, T t){
        if (isEndDynamicArray()) array = addBlockWithUseOtherMethod(array);
        if (array.length == 2 && pos == 0) {add(array[0]); array[0] = t; return;}
        //if (array.length == 2 && pos == 1) {array[1] = t; return;} //??????? при данной реал. если позиция указана больше длины массива то он его туда добавляет
        for (int i = findLastIndexValueDynamicArray(); i > pos - 1; i--){
            array[i+1] = array[i];
        }
        array[pos] = t;
    }

    public void remove(int pos){//добавить ограничения после создания метода size
        for (int i = pos; i < findLastIndexValueDynamicArray(); i++){
            array[i] = array[i+1];
        }
    }

    public T get(int pos) throws Exception{
        if (pos >= size()) {
            throw new Exception();
        }
        return array[pos];//добавить ограничения после создания метода size
    }

    public int size(){
        return findLastIndexValueDynamicArray() + 1;
    }

    public int realSize(){
        return array.length;
    }

    private int findLastIndexValueDynamicArray(){
        for (int i = 0; i < array.length; i++){
            if  (array[i] == null) return (i);
        }
        return 0;
    }

    private T[] addBlockWithUseOtherMethod(T[] array){
       array  = Arrays.copyOf(array, array.length * 2);
       return array;
    }

    private boolean isEndDynamicArray(){
        return ((array[array.length - 1]) != null);
    }




    @Override
    public String toString() {
        String result = "[";
        for (T t: array) {
            if (t == null) break;
            result += t + ", ";
        }
        result = result.substring(0, result.length()-2) + "]";
        return "DynamicArray=" +
                result;
    }


    public static void main(String[] args) throws Exception {
//        Integer[] arr = new Integer[10];
//        System.out.println(Arrays.toString(arr));
//        List list = new ArrayList();
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(12);
        dynamicArray.add(3);
        dynamicArray.add(5);
        dynamicArray.add(6);
        System.out.println("Метод add(): " + dynamicArray.toString());
        dynamicArray.insert(3,122);// можно ли вставить в конец;
        System.out.println("Метод insert(): " + dynamicArray.toString());
        dynamicArray.remove(3);
        System.out.println("Метод remove(): " + dynamicArray.toString());
        System.out.println("Метод get(): " + dynamicArray.get(2));
        System.out.println("Метод size(): " + dynamicArray.size());
        System.out.println("Метод realSize(): " + dynamicArray.realSize());
    }
}//азобраться с методом findLastIndexValueDynamicArray()
