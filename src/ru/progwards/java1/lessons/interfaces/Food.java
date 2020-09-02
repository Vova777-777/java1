package ru.progwards.java1.lessons.interfaces;

import static ru.progwards.java1.lessons.interfaces.CompareWeight.CompareResult.*;

public class Food implements CompareWeight {
    private int weight;

    public Food(int weight) {
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public static void main(String[] args) {
        System.out.println(new Food(3000).compareWeight(new Food(2000)));
    }


    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Food food;
        food = (Food) smthHasWeigt;
        CompareResult result;
        boolean less1 = this.weight < food.getWeight() ;
        boolean equal1 = this.weight == food.getWeight();
        if (less1) result = LESS;
        else
        if (equal1) result = EQUAL;
        else
            result = GREATER;
        return result;
    }
}
