package ru.progwards.java1.lessons.basics;

public class Astronomy {
    public static Double sphereSquare(Double r){     // площадь сферы
        double pi = 3.14;
        double S = 4 * pi * Math.pow(r, 2);
        return S;
    }

    public static Double earthSquare(){     // площадь Земли
       double earthS = sphereSquare(6_371.2);
       return earthS;
    }

    public static Double mercurySquare(){
        double mercuryS = sphereSquare(2_439.7);
        return  mercuryS;
    }

    public static Double jupiterSquare(){
        double jupiterS = sphereSquare(71_492.00);
        return jupiterS;
    }

    public static Double earthVsMercury(){
        return earthSquare()/mercurySquare();
    }

    public static Double earthVsJupiter(){
        return earthSquare()/jupiterSquare();
    }

}
