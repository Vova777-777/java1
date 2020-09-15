package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

public class Coder {

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        ForWrite writer = new ForWrite();
        ForWriteMistake writerMistake = new ForWriteMistake();
        FileReader fileReader;
        try {
            fileReader = new FileReader(inFileName);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextInt()){
                int symbol = scanner.nextInt();
                writer.writerFile(outFileName, code[symbol], logName);
              }
            try {
                fileReader.close();
            } catch (IOException e) {
            writerMistake.writerMistakeFile(logName, e);
            }
        }catch (FileNotFoundException e){
            writerMistake.writerMistakeFile(logName, e);
        }
    }



    public static void main(String[] args){
        char[] array = new char[10];
        array[0] = '!';
        array[1] = 'п';
        array[2] = 'а';
        array[3] = 'п';
        array[4] = 'а';
        codeFile("B://New.txt","B://11.txt", array, "B://Mistakes.txt");

    }
}
