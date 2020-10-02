package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<E> implements Iterator<E>{
    private E[][] array;
    int count1 = 0;
    int count2 = -1;

    MatrixIterator(E[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        if (this.count2 == array[count1].length - 1 && this.count1 == array.length - 1)// TODO Auto-generated method stub
            return false;
        else return true;
    }

    @Override
    public E next() {

        if (this.count2 == array[count1].length-1){count1++; count2 = -1;}
        count2++;
        return this.array[count1][count2];
    }

    public static void main(String[] args) {
        Integer[][] array = new Integer[6][3];
        array[0][0] = 1;
        array[0][1] = 2;
        array[0][2] = 3;
        array[1][0] = 4;
        array[1][1] = 5;
        array[1][2] = 6;
        array[2][0] = 7;
        array[2][1] = 8;
        array[2][2] = 9;
        array[3][0] = 10;
        array[3][1] = 11;
        array[3][2] = 12;
        array[4][0] = 13;
        array[4][1] = 14;
        array[4][2] = 15;
        array[5][0] = 16;
        array[5][1] = 17;
        array[5][2] = 18;


        MatrixIterator iterator = new MatrixIterator(array);
        while (iterator.hasNext()){
            String a = iterator.next().toString();
            System.out.println(a);
        }

    }
}
