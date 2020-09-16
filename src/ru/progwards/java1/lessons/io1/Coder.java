package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coder {

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        ForWrite writer = new ForWrite();
        ForWriteMistake writerMistake = new ForWriteMistake();
        FileReader fileReader;
        try {
            fileReader = new FileReader(inFileName);
            Scanner scanner = new Scanner(fileReader);
            List buffer = new ArrayList();
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
