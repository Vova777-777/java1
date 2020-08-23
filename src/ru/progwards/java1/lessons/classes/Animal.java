package ru.progwards.java1.lessons.classes;

public class Animal {

    private double weight;

    public static void main(String[] args) {
        System.out.println(new Animal(500).toStringFull());
        System.out.println(new Cow(150).toStringFull());
        System.out.println(new Hamster(2.5).toStringFull());
        System.out.println(new Duck(3).toStringFull());
        System.out.println(new Duck(2.7).toString());
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
}
