package ru.progwards.java1.lessons.interfaces;

import java.util.Objects;


public class Animal implements FoodCompare {

    private double weight;

    public static void main(String[] args) {
        System.out.println(new Animal(500).toStringFull());
        System.out.println(new Cow(150).toStringFull());
        System.out.println(new Hamster(2.5).toStringFull());
        System.out.println(new Duck(3).toStringFull());
        System.out.println(new Duck(2.7).toString());
       //  провкрка к заданию Н8
        Duck duck = new Duck(5);
        Hamster hamster = new Hamster(5);
        System.out.println(duck.equals(hamster));
        System.out.println(new Animal(10).equals(new Animal(100)));
        System.out.println(new Animal(20).getFood1kgPrice());
        System.out.println("цена еды коровы " + new Cow(1D).getFood1kgPrice());
        System.out.println("цена еды утки " + new Duck(1D).getFood1kgPrice());
        System.out.println(" " + new Animal(1D).compareFoodPrice(new Cow(1D)));
    }

    public Animal(double weight){// 1.0 Реализовать конструктор
this.weight = weight;
    }

    public AnimalKind getKind(){// 1.1 Метод
return AnimalKind.ANIMAL;
    }

    public FoodKind getFoodKind(){// 1.2 Метод
        return FoodKind.UNKNOWN;
    }

    public String toString(){// 1.3 Метод
        return "I am " + getKind() + "," + " eat " + getFoodKind();
    }

    public double getWeight(){// 2.1

        return weight;
    }

    public double getFoodCoeff(){// 2.2
return 0.02;
    }

    public double calculateFoodWeight(){// 2.6
       return (getWeight() * getFoodCoeff());
    }

    public String toStringFull(){// 2.7
        return "I am " + getKind() +  "," + " eat " + getFoodKind() + " " + calculateFoodWeight();
    }
    // задание Н8
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    public double getFood1kgPrice(){
        switch (getFoodKind()) {
            case UNKNOWN:
                return 0.0;
            case HAY:
                return 20.0;
            case CORN:
                return 50.0;
            default: return 0;
        }
    }

    public double getFoodPrice(){
        return calculateFoodWeight() * getFood1kgPrice();
    }

    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(this.getFoodPrice(),animal.getFoodPrice());
    }
}
