package ru.progwards.java1.lessons.io2;



public class PhoneNumber {

    public static String format(String phone) throws Exception {
       String onlyNumPhone = "";
       for (int i = 0; i < phone.length(); i++){
           onlyNumPhone = onlyNumPhone + getNum(phone.substring(i,i+1));
       }
        System.out.println(onlyNumPhone);

       if (onlyNumPhone.length() > 11 || onlyNumPhone.length() <10) {throw new Exception();}
       String firstValueOfNumber = "+7";
       if (onlyNumPhone.length() == 11) onlyNumPhone = firstValueOfNumber + onlyNumPhone.substring(1);
       else onlyNumPhone = firstValueOfNumber + onlyNumPhone;
        System.out.println(onlyNumPhone);
        StringBuilder stringBuilder = new StringBuilder(onlyNumPhone.substring(0,2)+ "(" + onlyNumPhone.substring(2,5) + ")" + onlyNumPhone.substring(5,8) + "-" + onlyNumPhone.substring(8));
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String getNum(String a){
        switch (a){
            case "0": return a;
            case "1": return a;
            case "2": return a;
            case "3": return a;
            case "4": return a;
            case "5": return a;
            case "6": return a;
            case "7": return a;
            case "8": return a;
            case "9": return a;
            default: a = "";
        }

        return a;
    }

    public static void main(String[] args) throws Exception {

        format("7(108)3630950");

    }
}
