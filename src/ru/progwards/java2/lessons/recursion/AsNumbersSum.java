package ru.progwards.java2.lessons.recursion;

//Реализовать класс, AsNumbersSum, содержащий метод
//public static String asNumbersSum(int number), который раскладывает параметр number, как всевозможные уникальные
//        комбинации сумм натуральных чисел, например:
//        5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1
//        5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1
//        Строка должна содержать результат, отформатированный точно, как указано в примере.
//        Повторные комбинации не допускаются, например, если а строке уже есть 3+2, то 2+3 там быть не должно.
//        Задача должна быть решена методом рекурсии, циклы использовать запрещено.

public class AsNumbersSum {

// 7 = 6+1 = 5+2 = 4+3 = 5+1+1 = 4+2+1 = 3+3+1 = 4+1+1+1 = 3+2+1+1 = 3+1+1+1+1 = 2+2+1+1+1 = 2+1+1+1+1+1 = 1+1+1+1+1+1+1
// 7 = 6+1 = 5+2 = 4+3 = 5+1+1 = 4+2+1 = 3+3+1 = 4+1+1+1 = 3+2+1+1 = 3+1+1+1+1 = 2+2+1+1+1 = 2+1+1+1+1+1 = 1+1+1+1+1+1+1

    public static String asNumbersSum(int number){
        String result = "";
        String additionalStr = "";
        result = getNumberAsTwoSummand(number,result, number, additionalStr) +
                getNumberMoreThanTwoSummand(number, result, number, additionalStr);
        return number + result;
    }

    private static String getNumberAsTwoSummand(int number, String result, int firstNumber, String additionalStr) {
        if (isFinishCalculation(number, firstNumber)) return result;
        result = result + " = " + (number - 1) + "+" + (firstNumber - number + 1) + additionalStr;
        return getNumberAsTwoSummand(number-1, result, firstNumber, additionalStr);
    }

    private static String getNumberMoreThanTwoSummand(int number, String result, int firstNumber, String additionalStr){
        if (number == 1) return result;
        additionalStr += "+1";
        result = getNumberAsTwoSummand(number-1, result,number-1, additionalStr);
        return getNumberMoreThanTwoSummand(number -1, result, firstNumber, additionalStr);
    }

    private static boolean isFinishCalculation(int number, int firstNumber){
        return (firstNumber % 2 == 0 && number == firstNumber / 2) || (firstNumber % 2 != 0 && number == firstNumber / 2 + 1);
    }

    public static void main(String[] args) {
        System.out.println(asNumbersSum(7));

    }
}
