package ru.progwards.java2.lessons.recursion;

//1.1 Реализовать класс Goods - товар, содержащий
//        String name - наименование
//        String number - артикул
//        int available - количество на складе
//        double price - цена
//        Instant expired - срок годности

import java.time.Instant;

public class Goods {
    String name;
    String number;
    int available;
    double price;
    Instant expired;

    public Goods(String name, String number, int available, double price, Instant expired) {
        this.name = name;
        this.number = number;
        this.available = available;
        this.price = price;
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", available=" + available +
                ", price=" + price +
                ", expired=" + expired +
                '}';
    }

}
