package ru.progwards.java1.lessons.io1;

import java.io.*;
/* Сергей, подскажите в каком формате делать правильнее решение. Как сделал во второй задаче
 или как в третьей. Во второй создал отдельные классы для чтения и для создания файла, но с
  определенными параметрами у методов, т.е спектр применения методов все равно не слишком широк,
   а в третьей решил все в одном классе но разными методами. И в третьей задаче ключевой метод статический
   а два других нет. Можно ли было их сделать статическими тоже или правильнее как сделал я.*/


public class Coder {

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        ForWrite writer = new ForWrite();
        ForWriteMistake writerMistake = new ForWriteMistake();
        FileReader fileReader;
        try {
            fileReader = new FileReader(inFileName);
            while (fileReader.ready()){
                int symbol = fileReader.read();

               try {
                   writer.writerFile(outFileName, code[symbol], logName);
               }catch (ArrayIndexOutOfBoundsException e){
                   writerMistake.writerMistakeFile(logName, e);
               }
              }
            try {
                fileReader.close();
            } catch (IOException e) {
            writerMistake.writerMistakeFile(logName, e);
            }
        }catch (IOException e){
            writerMistake.writerMistakeFile(logName, e);
        }
    }



    public static void main(String[] args){
        char[] array = new char[10];
        array[33] = '!';//33
        array[1087] = 'п';//1087
        array[1072] = 'а';//1072
        array[3] = 'п';
        array[4] = 'а';
        codeFile("B://New.txt","B://New1.txt", array, "B://Mistakes.txt");

    }
}
