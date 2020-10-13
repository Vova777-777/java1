package ru.progwards.java1.lessons.queues;

public class Order {
    /*2.2 Создать приватное свойство double sum  - сумма заказа*/
    private double sum;
    /*2.3 Создать приватное свойство int num  - номер заказа*/
    private int num = 0;
    private static int forNum = 0;



    /*2.4 Создать конструктор public Order(double sum) - для номера заказа создать систему автонумерации, начиная с 1*/
    public Order(double sum){
        this.sum = sum;
        forNum++;
        num = forNum;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                '}';
    }

    /*2.5 Создать геттер public double getSum()*/
    /*2.6 Создать геттер public int getNum()*/
}
