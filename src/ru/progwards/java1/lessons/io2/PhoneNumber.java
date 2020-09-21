package ru.progwards.java1.lessons.io2;



public class PhoneNumber {

    public static String format(String phone) throws Exception {
       StringBuilder phoneNumber = new StringBuilder(phone);
       getOnlyNumbers(phoneNumber);
       if (phoneNumber.length() > 11 || phoneNumber.length() <10) {throw new Exception();}
       if (phoneNumber.length() == 11) phoneNumber.insert(0,'+');
       else phoneNumber.insert(0,"+7");
       phoneNumber.insert(2,"(");
       phoneNumber.insert(6,")");
       phoneNumber.insert(10,"-");

        return phoneNumber.toString();
    }

    public static StringBuilder getOnlyNumbers(StringBuilder PhoneNumber){
        for (int i = 0; i < PhoneNumber.length(); i++) {
            switch (PhoneNumber.substring(i,i+1)) {
                case "0": break;
                case "1": break;
                case "2": break;
                case "3": break;
                case "4": break;
                case "5": break;
                case "6": break;
                case "7": break;
                case "8": break;
                case "9": break;
                default: PhoneNumber.deleteCharAt(i);
            }
        }
        return PhoneNumber;
    }

    public static void main(String[] args)  {
       try {
           System.out.println(format("7(108)3630880"));
       }catch (Exception e){
           System.out.println("введите правильное количество цифр вашего номера");
       }

    }
}
