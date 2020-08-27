package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;


public class DIntArray {
    private int[] array;

    DIntArray() {
        array = new int[0];
    }


    public void add(int num) {
        int[] arrayResult = new int[array.length + 1];
        arrayResult[arrayResult.length - 1] = num;
        System.arraycopy(array, 0, arrayResult, 0, array.length);

       array = arrayResult;
    }


    public void atInsert(int pos, int num) {
        int[] arrayResult = new int[array.length + 1];
        arrayResult[pos] = num;
        System.arraycopy(array, 0, arrayResult, 0, pos);
        System.arraycopy(array, pos, arrayResult, pos + 1, arrayResult.length - 1 - pos);
        array = arrayResult;
    }

    public void atDelete(int pos) {
        int[] arrayResult = new int[array.length - 1];
        System.arraycopy(array, 0, arrayResult, 0, pos);
        System.arraycopy(array, pos + 1, arrayResult, pos, arrayResult.length - pos);
        array = arrayResult;
    }

    public int at(int pos) {
        return array[pos];
    }

    public static void main(String[] args) {
        DIntArray dint = new DIntArray();
        dint.add(51);
        dint.add(52);
        dint.add(53);
        dint.add(54);
        dint.add(55);
        dint.add(56);
        dint.add(57);
        dint.add(58);
        dint.add(59);
        System.out.println(Arrays.toString(dint.array));
        dint.atInsert(2, 7);
        System.out.println(Arrays.toString(dint.array));
        dint.atDelete(2);
        System.out.println(Arrays.toString(dint.array));
        System.out.println(dint.at(2));
    }
}


