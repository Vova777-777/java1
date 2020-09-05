package ru.progwards.java1.lessons.interfaces;


import static ru.progwards.java1.lessons.interfaces.CompareWeight.CompareResult.*;

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
        sort(animals);
        for (int i = 0; i < 5; i++) {
            System.out.print(animals[i] + ", ");
        }
        System.out.println();

        Food[] foods = new Food[5];
        Food food1 = new Food(100);
        foods[1] = food1;
        Food food2 = new Food(90);
        foods[2] = food2;
        Food food3 = new Food(105);
        foods[3] = food3;
        Food food4 = new Food(380);
        foods[4] = food4;
        Food food5 = new Food(190);
        foods[0] = food5;
        sort(foods);
        for (int i = 0; i < 5; i++) {
            System.out.print(foods[i] + ", ");
        }
    }

    public static void sort(CompareWeight[] a){

   // теперь работает и для Food и для Animal.  не понимал как обеспечить доступ длясравнения веса,
   // пока не догнал, чио сравнение у классов уже сделано через интерфейс.
        for ( int i = 0; i < a.length - 1; i++){
            for (int j = i + 1; j < a.length; j++){
                CompareWeight k;
                if (a[i].compareWeight(a[j]) == CompareResult.GREATER) {k = a[i]; a[i] = a[j]; a[j] = k;}
            }
        }
    }

    //здесь надо переопределять? Или как вообще нам работать если метод интерфейса не нужен?
    //просто занулить?
    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {

      return null;
    }
}
