package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {

    /*  Просьба проверить является ли рабочим и если нет, то где ошибка. Если есть возможность как отред - ть, нет, то просто заброковать.
    public static int checkBit(byte value, int bitNumber){
int bit;
        String strValue = Integer.toBinaryString(value);//СТРОКА в двоичном виде value
try {
    bit = Integer.parseInt(String.valueOf(strValue.charAt(strValue.length() - 1 - bitNumber)));//индекс с конца СТРОКИ,
    // вытаскиваю символ из СТРОКИ, перевожу в строку, перевожу в число, получаю БИТ.
}catch (StringIndexOutOfBoundsException e){
    return 0;
}
        return bit;
    }
    */

    public static int checkBit(byte value, int bitNumber){
        int numberForGetLastBit = value >> bitNumber;
        int bit = numberForGetLastBit & 1;

        return bit;
    }

    public static void main(String[] args) {
        System.out.println(checkBit((byte) 3,3));
    }
}
