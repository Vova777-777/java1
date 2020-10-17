package ru.progwards.java1.lessons.maps;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UsageFrequency {
/*Реализовать класс, подсчитывающий частоту использования слов и букв в словах на основе текстов. Методы:
2.1 public void processFile(String fileName) - загрузить содержимое файла
2.2 public Map<Character, Integer> getLetters() - вернуть Map, который содержит все найденные буквы и цифры, и
количество раз, которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и др не учитывать.
2.3 public Map<String, Integer> getWords() - вернуть Map, который содержит все найденные слова и количество раз,
которое каждое слово встретилось. Знаки препинания, такие как “.,!? @” и др являются разделителями.
2.4 Протестировать на файле wiki.train.tokens (во вложении), для отладки можно использовать wiki.test.tokens
*/
    List<Character> list = new ArrayList<>();
    public String str = "";
    StringBuilder stringBuilder = new StringBuilder("");

    public void processFile(String fileName){
        try (FileReader reader = new FileReader(fileName)){
            int a = 0;
            while (reader.read() != -1)
                a = reader.read();
             str = "" + (char) a;
        }catch (IOException e){
            System.out.println(e);
        }
        str.replaceAll("\\p{Punct}","");
    }

    public Map<Character, Integer> getLetters(){
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        str.replaceAll(" ", "");
        list.clear();
        for (int i = 0; i < str.length(); i++){
            list.add(str.charAt(i));
        }
        for (int i = 0; i < list.size(); i++){
            count = Collections.frequency(list, list.get(i));
            map.put(list.get(i), count);
        }
        return map;
    }

    public Map<String, Integer> getWords(){
        Map<String, Integer> map = new HashMap<>();
        List<String> listString;
        String[] arr = str.split(" ");
        listString = Arrays.asList(arr);
       int count = 0;
       for (int i = 0; i < listString.size(); i++){
           count = Collections.frequency(listString, listString.get(i));
           map.put(listString.get(i), count);
       }
       return map;

    }

    public static void main(String[] args) {
        UsageFrequency usageFrequency = new UsageFrequency();
        usageFrequency.processFile("B://1//wiki.test.tokens");
        usageFrequency.getLetters();
        usageFrequency.getWords();
        System.out.println(usageFrequency.getWords());
    }

}
