package ru.progwards.java2.lessons.builders;

public class IntOverflowException extends RuntimeException{
    int a;
    int b;

    public IntOverflowException(int a, int b, String message) {
        super(message);
        this.a = a;
        this.b = b;
    }
}
