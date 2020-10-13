package ru.progwards.java1.lessons.queues;

import jdk.jfr.StackTrace;

import java.util.*;

public class OrderQueue {
    /*2.7 Создать метод, public void add(Order order), размещающий заказы в очередь с приоритетом, разбивая их
    по 3-м классам
    3 - заказы до 10000 руб включительно
    2 - заказы от 10000 до 20000 руб включительно
    1 - заказы от 20000 руб*/
    /*2.8 Создать метод, public Order get(), возвращающий первый заказ в очереди для обслуживания. Вначале
    обслуживаются заказы класса 1, потом 2, потом 3. Внутри каждого класса заказы должны обслуживаться в порядке
    поступления (по номеру заказа). Метод не выбрасывает исключения, возвращает null в случае пустой очереди.*/
    /*Продумать, и, при необходимости, добавить в классы нужные методы и свойства, для того, чтобы реализовать
    эту задачу.*/
    Queue<Order> orderLessThenTen = new ArrayDeque<>();
    Queue<Order> orderBetweenTenAndTwenty = new ArrayDeque<>();
    Queue<Order> orderMoreThenTwenty = new ArrayDeque<>();

    public void add(Order order){

        if (order.getSum() <= 10000) orderLessThenTen.offer(order);
        if (order.getSum() > 10000 && order.getSum() <= 20000) orderBetweenTenAndTwenty.offer(order);
        if (order.getSum() > 20000) orderMoreThenTwenty.offer(order);
    }

    public Order get() throws NullPointerException {
            if (!(orderMoreThenTwenty.isEmpty())) return orderMoreThenTwenty.poll();
            if (!(orderBetweenTenAndTwenty.isEmpty())) return orderBetweenTenAndTwenty.poll();
            else return orderLessThenTen.poll();
    }

    public static void main(String[] args) {
        Order order1 = new Order(21000);
        Order order11 = new Order(30000);
        Order order111 = new Order(35000);
        Order order2 = new Order(11000);
        Order order22 = new Order(12000);
        Order order3 = new Order(100);
        Order order33 = new Order(125);
        Order order333 = new Order(1000);

        OrderQueue orderQueue = new OrderQueue();
        orderQueue.add(order1);
        orderQueue.add(order11);
        orderQueue.add(order111);
        orderQueue.add(order2);
        orderQueue.add(order22);
        orderQueue.add(order3);
        orderQueue.add(order33);
        orderQueue.add(order333);

        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
        System.out.println(orderQueue.get().toString());
    }


}
