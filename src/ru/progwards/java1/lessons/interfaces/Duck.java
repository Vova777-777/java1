package ru.progwards.java1.lessons.interfaces;

import ru.progwards.java1.lessons.classes.Animal;
import ru.progwards.java1.lessons.classes.AnimalKind;
import ru.progwards.java1.lessons.classes.FoodKind;

public class Duck extends Animal {

    public Duck(double weight) {
        super(weight);
    }

    @Override
    public ru.progwards.java1.lessons.classes.AnimalKind getKind() {// 1.8
        return AnimalKind.DUCK;
    }

    @Override
    public FoodKind getFoodKind() {// 1.9
        return FoodKind.CORN;
    }

    @Override
    public double getFoodCoeff() {// 2.5
        return 0.04;
    }
}
