package ru.progwards.java1.lessons.io1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LineCount {
    static File file = new File("B//", "New.txt");
    public static int calcEmpty(String fileName) throws FileNotFoundException {

        try {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        int count = 0;
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(str);
            if (str.isEmpty()) count++;
        }
        fileReader.close();
        return count;
        }catch (Exception e){
            return -1;

        }
    }

    public static void main(String[] args) throws FileNotFoundException {

    }
}
