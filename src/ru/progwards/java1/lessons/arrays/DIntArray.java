package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;


public class DIntArray {
    private static int[] array;

    DIntArray() {
    }


    public void add(int num) {
        int[] arrayResult = new int[array.length + 1];
        arrayResult[arrayResult.length - 1] = num;
        System.arraycopy(array, 0, arrayResult, 0, array.length);

        System.out.println(Arrays.toString(arrayResult));
    }


    public void atInsert(int pos, int num) {
        int[] arrayResult = new int[array.length + 1];
        arrayResult[pos] = num;
        System.arraycopy(array, 0, arrayResult, 0, pos);
        System.arraycopy(array, pos, arrayResult, pos + 1, arrayResult.length - 1 - pos);
    }

    public void atDelete(int pos) {
        int[] arrayResult = new int[array.length - 1];
        System.arraycopy(array, 0, arrayResult, 0, pos);
        System.arraycopy(array, pos + 1, arrayResult, pos, arrayResult.length - pos);
    }

    public int at(int pos) {
        return array[pos];
    }
}


