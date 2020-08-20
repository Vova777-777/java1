package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {
    public static int maxSide(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }

    public static int minSide(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }

    public static boolean isEquilateralTriangle(int a, int b, int c){
        if (a == b && b == c) return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(maxSide(47,55,2));
        System.out.println(minSide(47,55,2));
        System.out.println(isEquilateralTriangle(4,4,4));
    }
}
