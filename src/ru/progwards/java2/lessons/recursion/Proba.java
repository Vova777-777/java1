package ru.progwards.java2.lessons.recursion;
// 7= 6+1= 5+2= 5+1+1= 4+3= 4+2+1= 4+1+1+1= 3+3+1= 3+2+2= 3+2+1+1= 3+1+1+1+1= 2+2+2+1= 2+2+1+1+1= 2+1+1+1+1+1= 1+1+1+1+1+1+1
//6152=51143=421=4111412=411134=331=3211=311113121=31111322=3211
public class Proba {
    static private Integer count = 0;

    public static String asNumbersSum(int number) {
        int num = 1;
        String result = "";
        String additionalStr = "";
        result = "";
        return "";//getNumberAsTwoSummand(number,0,"", ""); //+ dop(number/2+1, number/2, "", "");
    }



//    private static String getNumberAsTwoSummand(int number, String result, int firstNum) {
//
//    }



//    private static String dop(int firstSummand, int secondSummand, String result, String res) {
//
//        firstSummand -= 1;
//        secondSummand += 1;
//        if (count == 7) return result;
//        if (firstSummand == 1) return result;
//
//        String result1 = firstSummand + "+" + secondSummand;
//
//        if (secondSummand != 1) res = "=" + firstSummand + "+" + dop(secondSummand, 0,"", res);
//        result += res;
//        res = "";
//        count++;
//        return dop(firstSummand, secondSummand, result, res);
//    }

    private static String method(int firstSummand, int secondSummand, String result){
        if (firstSummand == 0) return result;
        firstSummand -= 1;
        secondSummand += 1;
        result = firstSummand + "" +  secondSummand;
       if (secondSummand != 1) result +=firstSummand+ "" + getNumberAsTwoSummand(firstSummand+ "",secondSummand, "", secondSummand,"");
        return result += method(firstSummand, secondSummand, "" );

    }

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
        count--;
        return getNumberAsTwoSummand(firstAddStr, number-1, result, firstNumber, additionalStr);


    }

//    private static boolean isFinishCalculation(int number, int firstNumber){
//        return (firstNumber % 2 == 0 && number == firstNumber / 2) || (firstNumber % 2 != 0 && number == firstNumber / 2 + 1);
//    }








    public static void main(String[] args) {
        System.out.println(getNumberAsTwoSummand("",7,"", 7,""));
        System.out.println(method(7, 0, ""));
        //System.out.println(new AsNumbersSum().methodWithMinus1("", 7));

    }
}
