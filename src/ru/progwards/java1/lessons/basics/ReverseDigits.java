package ru.progwards.java1.lessons.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

public class ReverseDigits {

    public static int reverseDigits(int number) {
        String strNumber = Integer.toString(number);
        StringBuffer buffer = new StringBuffer(strNumber);
        String strNumberReverse = buffer.reverse().toString();
        int numberReverse = Integer.valueOf(strNumberReverse);
        return numberReverse;
    }
}


