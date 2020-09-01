package ru.progwards.java1.lessons.interfaces;

import ru.progwards.java1.lessons.classes.Animal;
import ru.progwards.java1.lessons.classes.AnimalKind;
import ru.progwards.java1.lessons.classes.FoodKind;

public class Cow extends Animal {

    public Cow(double weight) {
        super(weight);
    }

    @Override
    public ru.progwards.java1.lessons.classes.AnimalKind getKind() {// 1.4
        return AnimalKind.COW;
    }

    @Override
    public ru.progwards.java1.lessons.classes.FoodKind getFoodKind() {// 1.5
        return FoodKind.HAY;
    }

    @Override
    public double getFoodCoeff() {// 2.3
        return 0.05;
    }
}
