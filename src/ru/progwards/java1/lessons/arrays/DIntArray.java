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

        System.out.println(Arrays.toString(arrayResult));
    }

    public void atDelete(int pos) {
        int[] arrayResult = new int[array.length - 1];
        System.arraycopy(array, 0, arrayResult, 0, pos);
        System.arraycopy(array, pos + 1, arrayResult, pos, arrayResult.length - pos);

        System.out.println(Arrays.toString(arrayResult));
    }

    public int at(int pos) {
        return array[pos];
    }


    public static void main(String[] args) {
        array = new int[]{1, 3, 5, 3, 5, 6, 8, 10};
        System.out.println(Arrays.toString(array));
        DIntArray dint = new DIntArray();
        dint.add(7);
        dint.atInsert(1, 7);
        dint.atDelete(3);
        System.out.println(dint.at(1));
    }
}


