package ru.progwards.java1.lessons.interfaces;

import java.util.ArrayList;

public class CalculateFibonacci {

    private static CacheInfo lastFibo = new CacheInfo();

    public static class CacheInfo{

        public int n = 0;
        public int fibo = 0;
    }

    public static void main(String[] args) {
        System.out.println(fiboNumber(1));
        System.out.println(getLastFibo().n);
        System.out.println(getLastFibo().fibo);
        clearLastFibo();
        System.out.println(getLastFibo());
         System.out.println(fiboNumber(15));
    }

    public static int fiboNumber(int n) {
        if (lastFibo == null) lastFibo = new CacheInfo();
         if (n == lastFibo.n) return lastFibo.fibo;
        else {
            ArrayList<Integer> listFibo = new ArrayList<>();
            listFibo.add(1);
            listFibo.add(1);
            for (int i = 2; i <= n; i++) {
                listFibo.add(listFibo.get(i - 2) + listFibo.get(i - 1));
            }
            lastFibo.n = n;
            lastFibo.fibo = listFibo.get(n - 1);
            return lastFibo.fibo;
        }

    }

    public static CacheInfo getLastFibo(){
        return lastFibo;
    }

    public static void clearLastFibo(){
        lastFibo = null;
    }

}
