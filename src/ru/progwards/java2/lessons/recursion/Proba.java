package ru.progwards.java2.lessons.recursion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

// 7= 6+1= 5+2= 5+1+1= 4+3= 4+2+1= 4+1+1+1= 3+3+1= 3+2+2= 3+2+1+1= 3+1+1+1+1= 2+2+2+1= 2+2+1+1+1= 2+1+1+1+1+1= 1+1+1+1+1+1+1
//1+1+1+1+1+1+1] [1+1+1+1+1+2] [1+1+1+1+3] [1+1+1+2+2] [1+1+1+4] [1+1+2+3] [1+1+5] [1+2+2+2] [1+2+4] [1+3+3] [1+6] [2+2+3] [2+5] [3+4]
public class Proba {
    static private Integer count = 0;

    public static String asNumbersSum(int number) {
        int num = 1;
        String result = "";
        String additionalStr = "";
        result = "";
        return "";//getNumberAsTwoSummand(number,0,"", ""); //+ dop(number/2+1, number/2, "", "");
    }

//    private static String method(int firstSummand, int secondSummand, String result){
//        if (firstSummand == 1) return result;
//        firstSummand -= 1;
//        secondSummand += 1;
//        if (secondSummand != 1) result += getNumberAsTwoSummand(firstSummand+ "",secondSummand, "", secondSummand,"");
//        result += firstSummand + "" +  secondSummand;
//
//        return result += method(firstSummand, secondSummand, "" );
//
//    }

    private static String getNumberAsTwoSummand(String firstAddStr, int number, String result, int firstNumber, String additionalStr) {
        count++;
        int firstSummand = number - 1;
        int secondSummand = (firstNumber - number + 1);
        result +="="+ firstAddStr +  firstSummand + "" + secondSummand + additionalStr;
        String result1 ="";
        if (!("" + secondSummand).equals("1")){
            firstAddStr +=""+firstSummand;
            result +=getNumberAsTwoSummand(firstAddStr, secondSummand, "", secondSummand, additionalStr);
            firstAddStr = firstAddStr.substring(0,firstAddStr.length()-1);
        }
        if (("" + secondSummand).equals("1")&&!(""+firstSummand).equals("1")&&count>1){
            additionalStr += + secondSummand; /*firstAddStr = firstAddStr.substring(0,firstAddStr.length()-1);*/
            result +=getNumberAsTwoSummand(firstAddStr, firstSummand, "", firstSummand, additionalStr);
            additionalStr = additionalStr.substring(0,additionalStr.length()-1);
        }
//        if (isFinishCalculation(firstSummand, secondSummand)){count--; return result;}
        if (firstSummand==1){count--; return result;}
//        if (firstSummand>=secondSummand){count--; return result;}
        count--;
        return getNumberAsTwoSummand(firstAddStr, number-1, result, firstNumber, additionalStr);
    }

    private static void meth1(String result){
        String[] arr1 = result.split("=");
        List<String> list = Arrays.asList(meth2(0,arr1));
        TreeSet<String> set = new TreeSet<>(list);
        System.out.println(set.toString());

    }

    private static String[] meth2(int index, String[] arr){
        if (index == arr.length - 1) return arr;
        String[] arr2 = arr[index].split("");

        Arrays.sort(arr2);
        arr[index] = Arrays.toString(arr2);
        index++;
        return meth2(index, arr);
    }



//    private static boolean isFinishCalculation(int number, int firstNumber){
//        return (firstNumber % 2 == 0 && number == firstNumber / 2) || (firstNumber % 2 != 0 && number == firstNumber / 2 + 1);
//    }








    public static void main(String[] args) {
        System.out.println(getNumberAsTwoSummand("",7,"", 7,""));
        //System.out.println(method(7, 0, ""));
        //System.out.println(new AsNumbersSum().methodWithMinus1("", 7));
        meth1(getNumberAsTwoSummand("",7,"", 7,""));

    }
}
//    public static String asNumbersSum(int number){
//        String result = "";
//        String additionalStr = "";
//        result = getNumberMoreThanTwoSummand(number,result, number, additionalStr) ;
//        return number + result;
//    }
//
//    private static String getNumberAsTwoSummand(int number, String result, int firstNumber, String additionalStr) {
//        if (isFinishCalculation(number, firstNumber)) return result;
//        result = result + " = " + (number - 1) + "+" + (firstNumber - number + 1) + additionalStr;
//        return getNumberAsTwoSummand(number-1, result, firstNumber, additionalStr);
//    }
//
//
//
//    private static String getNumberMoreThanTwoSummand(int number, String result, int firstNumber, String additionalStr){
//        if (number == 1) return result;
//        additionalStr += "+1";
//        result = getNumberAsTwoSummand(number-1, result,number-1, additionalStr);
//        return getNumberMoreThanTwoSummand(number -1, result, firstNumber, additionalStr);
//    }
//
//    private static boolean isFinishCalculation(int number, int firstNumber){
//        return (firstNumber % 2 == 0 && number == firstNumber / 2) || (firstNumber % 2 != 0 && number == firstNumber / 2 + 1);