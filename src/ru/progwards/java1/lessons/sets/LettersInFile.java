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
        Scanner scanner = new Scanner(Path.of(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
          stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        String str = stringBuilder.toString().replaceAll( " ", "");
        String[] array = str.replaceAll("\\p{Punct}","").split("");
        Set<String> set = new HashSet<>(Arrays.asList(array));
        String[] strings = new String[set.size()];
        set.toArray(strings);
        Arrays.sort(strings);
        stringBuilder.delete(0,stringBuilder.length());
        for (int i = 0; i < strings.length; i++){
            stringBuilder.append(strings[i]);
        }
        return stringBuilder.toString();
    }



    public static void main(String[] args) throws IOException {
        System.out.println(process("B://1//T"));
    }
}
