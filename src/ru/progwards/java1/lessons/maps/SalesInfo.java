package ru.progwards.java1.lessons.maps;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class SalesInfo {

    List<String> listGoods = new ArrayList<>();
    List<Double> listMoney = new ArrayList<>();
    List<String> listNamesBuyer = new ArrayList<>();
    List<Integer> listHowMany = new ArrayList<>();

    public int loadOrders(String fileName){
        int count = 0;
        try (Scanner scanner = new Scanner(Path.of(fileName), StandardCharsets.UTF_8)){
            int countOfInt;
            while (scanner.hasNextLine()){
                countOfInt = 0;
             String str = scanner.nextLine();
             String[] arr = str.split(", ");
             if (arr.length != 4) continue;
             if (checkStringIsNumber(arr[2])) countOfInt++;
             if (checkStringIsNumber(arr[3])) countOfInt++;
             if (countOfInt != 2) continue;
             else count++;
                listNamesBuyer.add(arr[0]);
                listGoods.add(arr[1]);
                listHowMany.add(Integer.valueOf(arr[2]));
                listMoney.add(Double.valueOf(arr[3]));
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return count;
    }

    public Boolean checkStringIsNumber(String str){
        try {
            Integer.valueOf(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    Map<String, Double> getGoods(){
        Map<String, Double> map = new TreeMap<>();
        for (int i = 0; i < listGoods.size(); i++){
            if (map.containsKey(listGoods.get(i))){
                map.replace(listGoods.get(i), map.get(listGoods.get(i)) + listMoney.get(i));}
            else map.put(listGoods.get(i), listMoney.get(i));
        }
        return map;
    }

    Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> map = new TreeMap<>();
        for (int i = 0; i < listHowMany.size(); i++){
            if (map.containsKey(listNamesBuyer.get(i))){
                AbstractMap.SimpleEntry<Double, Integer> entry = map.get(listNamesBuyer.get(i));
                map.replace(listNamesBuyer.get(i), new AbstractMap.SimpleEntry<>(entry.getKey() + listMoney.get(i),
                        entry.getValue() + listHowMany.get(i)));}
            else map.put(listNamesBuyer.get(i), new AbstractMap.SimpleEntry<>(listMoney.get(i), listHowMany.get(i)));
        }
        return map;
    }


    public static void main(String[] args) {
        SalesInfo salesInfo = new SalesInfo();
        System.out.println("Колличество строк  соответствующих формату: "
                + salesInfo.loadOrders("B://1//Test777.txt"));
        System.out.println(salesInfo.getGoods());
        System.out.println(salesInfo.getCustomers());

     }
}
