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
            while (scanner.hasNextLine()){
              String str = scanner.nextLine();
              String strForSymbols = "";
              for (int i = 0; i < str.length(); i++){
                  char symbol = str.charAt(i);// достаю символ из строки файла
                  for (int j = 0; j < code.length; j++){//кодирую символ и помещаю его в строку
                      if (symbol==code[j]) {strForSymbols = strForSymbols + j; break;}
                  }
              }
              writer.writerFile(outFileName, strForSymbols, logName);
            }try {
                fileReader.close();
            } catch (IOException e) {
            writerMistake.writerMistakeFile(logName, e);
            }
        }catch (FileNotFoundException e){
            writerMistake.writerMistakeFile(logName, e);
        }
    }


    public static void main(String[] args) throws IOException {
        char[] array = new char[10];
       array [0] = 'q';
       array [1] = 'w';
       array [2] = 'e';
       array [3] = 'r';
       array [4] = 't';
codeFile("B://New.t", "B://New1.txt", array, "B://Mistakes.txt" );


    }
}
