package ru.progwards.java1.lessons.io1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {

    public static int calcEmpty(String fileName)  {

        try {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        int count = 0;
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(str);
            if (str.isEmpty()) count++;
        }
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return -1;
            }
            return count;
        }catch (FileNotFoundException e){
           return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calcEmpty("999"));
    }
}


