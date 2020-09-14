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
            String strForSymbols = "";
            while (scanner.hasNextInt()){
                int symbol = scanner.nextInt();
                strForSymbols = strForSymbols + code[symbol];
              }
              writer.writerFile(outFileName, strForSymbols, logName);
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


    }
}
