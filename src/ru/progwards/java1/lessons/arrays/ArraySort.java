package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;
import java.util.Random;

public class ArraySort {

    public static void sort(int[] a){
        for ( int i = 0; i < a.length - 1; i++){
            for (int j = i + 1; j < a.length; j++){
                int k;
                if (a[i] > a[j]) {k = a[i]; a[i] = a[j]; a[j] = k;}
            }
        }
    }

    public static void main(String[] args) {
        int[] b = new int[50];
        Random random = new Random();
        for (int i = 0; i < b.length; i++) {
            b[i] = random.nextInt((200 - 1) + 1);
        }

        System.out.println(Arrays.toString(b));
        sort(b);
        System.out.println(Arrays.toString(b));
    }
}
