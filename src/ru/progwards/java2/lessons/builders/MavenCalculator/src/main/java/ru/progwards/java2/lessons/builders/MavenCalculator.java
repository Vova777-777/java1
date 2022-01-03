package ru.progwards.java2.lessons.builders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MavenCalculator {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
while (true){

        String strConnective = "";
        try {
            while(strConnective.equals("")) {
                System.out.println("Введите выражение для вычисления из двух чисел и арифмитического знака + или" +
                        "или - или * или / без пробелов или команду EXIT");
                strConnective = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            }
            if (strConnective.equalsIgnoreCase("EXIT")) return;

        String arrayNumbers[] = strConnective.split("[\\D]");
        String arrayMark[] = strConnective.split("[\\d]");
    String arithmeticalMark = "";
        for (int i = 0; i < arrayMark.length; i++){
            if (!arrayMark[i].equals(""))arithmeticalMark = arrayMark[i];
        }

        SimpleCalculator simpleCalculator = new SimpleCalculator();
        switch (arithmeticalMark) {
            case "+":
                System.out.println(simpleCalculator.sum(Integer.parseInt(arrayNumbers[0]),
                        Integer.parseInt(arrayNumbers[1])));
                break;
            case "-":
                System.out.println(simpleCalculator.diff(Integer.parseInt(arrayNumbers[0]),
                        Integer.parseInt(arrayNumbers[1])));
                break;
            case "*":
                System.out.println(simpleCalculator.mult(Integer.parseInt(arrayNumbers[0]),
                        Integer.parseInt(arrayNumbers[1]))); ;
                break;
            case "/":
                System.out.println(simpleCalculator.div(Integer.parseInt(arrayNumbers[0]),
                        Integer.parseInt(arrayNumbers[1])));
                break;
        }
}

}

    }


