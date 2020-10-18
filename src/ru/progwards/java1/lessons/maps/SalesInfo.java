package ru.progwards.java1.lessons.maps;
/*3.1 Реализовать метод public int loadOrders(String fileName) - вернуть количество успешно загруженных строк.
Если в строке более или менее 4-x полей, или количество и сумма не преобразуются в числа - эту строку не загружаем.
3.2 Реализовать метод public Map<String, Double> getGoods() - вернуть список товаров, отсортированный по наименованию
товара. В String - наименование товара, в Double - общая сумма продаж по товару
3.3 Реализовать метод public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() - вернуть список
покупателей, отсортированный по алфавиту. В String  - ФИ, в Double - сумма всех покупок покупателя, в Integer -
количество покупок
3.4 Протестировать на данных из примера ниже*/

/*Пример
Иванов Сергей, iPhone 10X, 2, 150000
Петрова Анна, наушники JBL, 2, 7000
Иванов Сергей, чехол для iPhone, 1, 1000
Петрова Анна, пакет пластиковый, 1, 3
Радж Кумар, батарейка ААА, 1, 150
Михаил Цикло, iPhone 10X, 1, 75000
Радж Кумар, пакет пластиковый, 1, 3
Михаил Цикло, пакет пластиковый, 1, 3
Иванов Сергей, стекло защитное, 1, 1000
Василий Пупкин, спички, 10, 10*/


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
            while (scanner.hasNextLine()){
             String str = scanner.nextLine();
             String[] arr = str.split(", ");
             if (arr.length != 4) break;
             if (checkStringIsNumber(arr[2]) == false) break;
             if (checkStringIsNumber(arr[3]) == false) break;
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
            int number = Integer.valueOf(str);
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
