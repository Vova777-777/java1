package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {
    public static void main(String[] args) {
        System.out.println(isIsoscelesTriangle(3,33,33));
    }

    public static boolean isTriangle(int a, int b, int c){
        if (a < (b + c) && b < (a + c) && c < (a + b)) return true;
        else return false;
    }

    public static boolean isRightTriangle(int a, int b, int c){
     if (Math.pow(a, 2) == Math.pow(b, 2) + Math.pow(c, 2)) return true;
     if (Math.pow(b, 2) == Math.pow(a, 2) + Math.pow(c, 2)) return true;
     if (Math.pow(c, 2) == Math.pow(a, 2) + Math.pow(b, 2)) return true;
     else return false;
    }

    public static boolean isIsoscelesTriangle(int a, int b, int c){
        if ( a == b || a == c || b == c) return true;
        else return false;
    }
}
