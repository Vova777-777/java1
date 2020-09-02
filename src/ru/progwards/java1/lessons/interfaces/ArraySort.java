package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;
import java.util.Random;

public class ArraySort implements CompareWeight {

    public static void main(String[] args) {
        Animal[] animals = new Animal[5];
        Animal animal1 = new Animal(100);
        animals[1] = animal1;
        Animal animal2 = new Animal(90);
        animals[2] = animal2;
        Animal animal3 = new Animal(105);
        animals[3] = animal3;
        Animal animal4 = new Animal(380);
        animals[4] = animal4;
        Animal animal5 = new Animal(190);
        animals[0] = animal5;
        CompareWeight.sort(animals);
        for (int i = 0; i < 5; i++) {
            System.out.print(animals[i] + ", ");
        }
    }




    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }
}
