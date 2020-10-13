package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;

public class StackCalc {
    /*Реализовать class StackCalc, который содержит стек чисел с плавающей точкой (double). Выбрать наиболее удобную
    для этого коллекцию. Реализовать методы работы со стеком:

3.1 public void push(double value) - положить value на вершину стека

3.2 public double pop() - взять (убрать) значение с вершины стека

3.3 public void add() - сложить 2 верхних значения на стеке, результат положить на стек. В итогу в стеке должно
быть на один элемент меньше

3.4 public void sub() - вычесть верхнее значение на стеке, из следующего по глубине, результат положить на стек.
В итоге в стеке должно быть на один элемент меньше

3.5 public void mul() - умножить 2 верхних значения на стеке, результат положить на стек.
В итогу в стеке должно быть на один элемент меньше

3.6 public void div() - поделить на верхнее значение на стеке, следующее по глубине, результат положить на стек.
В итоге в стеке должно быть на один элемент меньше

*/
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
