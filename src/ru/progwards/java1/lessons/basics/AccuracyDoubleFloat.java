package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
    public static final double pi = 3.14;
    public static double radius = 6371.2;


    public static double volumeBallDouble(double radius) {
        double V =  (4.00 / 3.00) * pi * Math.pow(radius, 3);
        return V;
    }

    public static float volumeBallFloat(float radius) {
        float V = (float) ((4.00 / 3.00) * pi * Math.pow(radius, 3));
        return V;
    }

    public static double calculateAccuracy(double radius) {
        return volumeBallDouble(radius) - volumeBallFloat((float) radius);
    }


    public static void main(String[] args) {
        System.out.println(volumeBallDouble(1));
        System.out.println(volumeBallFloat(1));
        System.out.println(calculateAccuracy(1));
    }
}