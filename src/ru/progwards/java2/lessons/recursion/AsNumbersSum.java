package ru.progwards.java2.lessons.recursion;

//Реализовать класс, AsNumbersSum, содержащий метод
//public static String asNumbersSum(int number), который раскладывает параметр number, как всевозможные уникальные
//        комбинации сумм натуральных чисел, например:
//        5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1
//        7= 6+1= 5+2= 5+1+1= 4+3= 4+2+1= 4+1+1+1= 3+3+1= 3+2+2= 3+2+1+1= 3+1+1+1+1= 2+2+2+1= 2+2+1+1+1= 2+1+1+1+1+1= 1+1+1+1+1+1+1
//        Строка должна содержать результат, отформатированный точно, как указано в примере.
//        Повторные комбинации не допускаются, например, если а строке уже есть 3+2, то 2+3 там быть не должно.
//        Задача должна быть решена методом рекурсии, циклы использовать запрещено.

import java.util.*;

public class AsNumbersSum {
// 7= 6+1= 5+2= 5+1+1= 4+3= 4+2+1= 4+1+1+1= 3+3+1= 3+2+2= 3+2+1+1= 3+1+1+1+1= 2+2+2+1= 2+2+1+1+1= 2+1+1+1+1+1= 1+1+1+1+1+1+1

    static private Integer count = 0;
    static TreeSet<String> setOfDifferentSum = new TreeSet<>();

    public static String asNumbersSum(int number){
        getNumberAsTwoSummands("", number, "", number, "") ;
        List<String> list = new ArrayList<>(setOfDifferentSum);
        setOfDifferentSum = new TreeSet<>();
        Collections.reverse(list);
        return number + String.join("", list);
    }

    private static String getNumberAsTwoSummands(String firstAddStr, int number, String result, int firstNumber, String additionalStr) {
        count++;
        int firstSummand = number - 1;
        int secondSummand = (firstNumber - number + 1);
        //result +=" = "+ firstAddStr +""+firstSummand + "+" + secondSummand + "+" + additionalStr;
        String sum = firstAddStr +  firstSummand + "+" + secondSummand + additionalStr;
        sum = EverySumToRightForm(sum);
        result =" = "+ sum;
        setOfDifferentSum.add(result);

        if (!("" + secondSummand).equals("1")){
            firstAddStr += "" +firstSummand+"+" ;
            result += getNumberAsTwoSummands(firstAddStr +"", secondSummand, "", secondSummand, additionalStr);
            firstAddStr = firstAddStr.substring(0,firstAddStr.length()-1-(""+firstSummand).length());
        }
        if (("" + secondSummand).equals("1")&&!(""+firstSummand).equals("1")&&count>1){
            additionalStr += "+" + secondSummand; /*firstAddStr = firstAddStr.substring(0,firstAddStr.length()-1);*/
            result += getNumberAsTwoSummands(firstAddStr, firstSummand, "", firstSummand, "" + additionalStr);
            additionalStr = additionalStr.substring(0,additionalStr.length()-2);
        }
//        if (isFinishCalculation(firstSummand, secondSummand)){count--; return result;}
        if (firstSummand==1){count--; return result;}
//        if (firstSummand>=secondSummand){count--; return result;}
        count--;
        return getNumberAsTwoSummands(firstAddStr, number-1, result, firstNumber, additionalStr);
    }

    private static String EverySumToRightForm(String str){
        List<String> list = Arrays.asList(str.split("\\+"));
        Collections.sort(list);
        Collections.reverse(list);
        String result = String.join("+", list);
        return result;
    }



//    private static void meth1(String result){
//        String[] arr1 = result.split("=");
//        List<String> list = Arrays.asList(meth2(0,arr1));
//        TreeSet<String> set = new TreeSet<>(list);
//        System.out.println(set.toString());
//
//    }
//
//    private static String[] meth2(int index, String[] arr){
//        if (index == arr.length - 1) return arr;
//        String[] arr2 = arr[index].split("");
//
//        Arrays.sort(arr2);
//        arr[index] = Arrays.toString(arr2);
//        index++;
//        return meth2(index, arr);
//    }





    public static void main(String[] args) {
       System.out.println(asNumbersSum(13));
    }
}
