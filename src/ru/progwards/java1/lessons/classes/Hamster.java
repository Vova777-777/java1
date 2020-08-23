package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal {
    public Hamster(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind() {// 1.6
        return AnimalKind.HAMSTER;
    }

    @Override
    public FoodKind getFoodKind() {// 1.7
        return FoodKind.CORN;
    }

    @Override
    public double getFoodCoeff() {// 2.4
        return 0.03;
    }
}
