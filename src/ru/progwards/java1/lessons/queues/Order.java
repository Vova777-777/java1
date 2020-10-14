package ru.progwards.java1.lessons.queues;

public class Order {

    private double sum;
    private int num = 0;
    private static int forAutoNum = 0;

    public Order(double sum){
        this.sum = sum;
        forAutoNum++;
        num = forAutoNum;
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
}
