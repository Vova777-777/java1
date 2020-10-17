package ru.progwards.java1.lessons.maps;

import java.io.IOException;
import java.nio.file.Path;
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
    StringBuilder stringBuilder1 = new StringBuilder("");
    StringBuilder stringBuilder2 = new StringBuilder("");

    public void processFile(String fileName){
        try (Scanner scanner = new Scanner(Path.of(fileName))){
            int a = 0;
            while (scanner.hasNextLine()) {

                stringBuilder1.append(scanner.nextLine());
            }
        }catch (IOException e){
            System.out.println(e);
        }
        str = stringBuilder1.toString().replaceAll("\\p{Punct}","").replaceAll("–","");
        stringBuilder2.append(str);

        stringBuilder1.delete(0, stringBuilder1.length());
        stringBuilder1.append(str);
        System.out.println(stringBuilder1.toString());//
    }

    public Map<Character, Integer> getLetters(){

        Map<Character, Integer> map = new TreeMap<>();
        int count = 0;
        String str1 = stringBuilder1.toString().replaceAll(" ", "");
        stringBuilder1.delete(0,stringBuilder1.length());
        stringBuilder1.append(str1);
        list.clear();
        for (int i = 0; i < stringBuilder1.length(); i++){
            list.add(stringBuilder1.toString().charAt(i));
        }
        for (int i = 0; i < list.size(); i++){
            count = Collections.frequency(list, list.get(i));
            map.putIfAbsent(list.get(i), count);
        }
        return map;
    }

    public Map<String, Integer> getWords(){
        Map<String, Integer> map = new TreeMap<>();
        List<String> listString;
        String[] arr = stringBuilder2.toString().split(" ");
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
        System.out.println(usageFrequency.getLetters());
        System.out.println(usageFrequency.getWords());
    }
}
