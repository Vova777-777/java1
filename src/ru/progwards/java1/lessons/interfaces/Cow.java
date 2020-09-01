package ru.progwards.java1.lessons.interfaces;



public class Cow extends Animal {

    public Cow(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind() {// 1.4
        return AnimalKind.COW;
    }

    @Override
    public FoodKind getFoodKind() {// 1.5
        return FoodKind.HAY;
    }

    @Override
    public double getFoodCoeff() {// 2.3
        return 0.05;
    }
}
