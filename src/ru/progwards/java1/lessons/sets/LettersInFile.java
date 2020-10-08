package ru.progwards.java1.lessons.sets;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LettersInFile {
/*3.1 Метод public static String process(String fileName) - вернуть все буквы, которые встретились в файле,
сконкатенированные в виде строки. Буквы должны быть упорядочены по алфавиту, типа “ADEF...”.
Все возникающие исключения, по работе с потоками, пробросить выше.*/
    public static String process(String fileName) throws IOException {
        Scanner scannerFile = new Scanner(Path.of(fileName));
        StringBuilder dataFromFile = new StringBuilder();
        while (scannerFile.hasNextLine()){
            dataFromFile.append(scannerFile.nextLine());
        }
        scannerFile.close();
        String strFormat = dataFromFile.toString().replaceAll( " ", "");
        String[] array = strFormat.replaceAll("\\p{Punct}","").split("");
        Set<String> setOnlyLetters = new HashSet<>(Arrays.asList(array));
        String[] arrayForSort = new String[setOnlyLetters.size()];
        setOnlyLetters.toArray(arrayForSort);
        Arrays.sort(arrayForSort);
        dataFromFile.delete(0,dataFromFile.length());
        for (int i = 0; i < arrayForSort.length; i++){
            dataFromFile.append(arrayForSort[i]);
        }
        return dataFromFile.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(process("B://1//T"));
    }
}
