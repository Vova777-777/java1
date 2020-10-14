package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;

public class StackCalc {

    ArrayDeque<Double> stack = new ArrayDeque<>();

    public void push(double value){
        stack.push(value);
    }

    public double pop(){
       return stack.pop();
    }

    public void add(){
        double a = pop();
        double b = pop();
        push(a + b);
    }

    void sub(){
        double a = pop();
        double b = pop();
        push(b - a);
    }

    void mul(){
        double a = pop();
        double b = pop();
        push(a * b);
    }

    void div(){
        double a = pop();
        double b = pop();
        push(b / a);
    }
}
