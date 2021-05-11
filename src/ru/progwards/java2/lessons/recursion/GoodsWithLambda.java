package ru.progwards.java2.lessons.recursion;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    List<Goods> list;

    public void setGoods(List<Goods> list){
        this.list = list;
    }

    public List<Goods> sortByName(){
        this.list.sort((a,b) -> a.name.compareToIgnoreCase(b.name));
        return list;
    }

    public List<Goods> sortByNumber(){
        this.list.sort((a, b) -> a.number.compareToIgnoreCase(b.number));
        return list;
    }

    public List<Goods> sortByPartNumber(){
        this.list.sort((a, b) -> a.number.substring(0, 3).compareToIgnoreCase(b.number.substring(0, 3)));
        return list;
    }

    //public List<Goods> sortByAvailabilityAndNumber() - вернуть список, отсортированный по количеству,
    // а для одинакового количества, по артикулу, без учета регистра
    //1.8 реализовать метод
    public List<Goods> sortByAvailabilityAndNumber(){
        List<Goods> list = this.list.stream().sorted((a, b) ->
                a.available == b.available ? a.number.compareToIgnoreCase(b.number) :
                        Integer.compare(a.available, b.available)).collect(Collectors.toList());
        return list;
    }

//    public List<Goods> expiredAfter(instant date) - вернуть список, с товаром, который будет просрочен после
//    указанной даты, отсортированный по дате годности

    public List<Goods> expiredAfter(Instant date){
        List<Goods> list = this.list.stream().filter(e -> e.expired.isAfter(date)).
                sorted(Comparator.comparing(a -> a.expired)).collect(Collectors.toList());
        return list;
    }

    //public List<Goods> сountLess(int count) - вернуть список, с товаром, количество на складе которого меньше у
    // казанного, отсортированный по количеству

    public List<Goods> сountLess(int count){
        return this.list.stream().sorted(Comparator.comparingInt(a -> a.available)).
                takeWhile(a -> a.available < count).collect(Collectors.toList());
    }

//    public List<Goods> сountBetween(int count1, int count2) - вернуть список, с товаром, количество на складе
//    которого больше count1 и меньше count2, отсортированный по количеству


    List<Goods> сountBetween(int count1, int count2){
        return this.list.stream().sorted(Comparator.comparing(a -> a.available)).filter(a -> a.available > count1).
                takeWhile(a -> a.available < count2).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Goods> list = new ArrayList<>(List.of(
    new Goods("first", "123456", 15, 170.0, Instant.ofEpochSecond(300000)),
    new Goods("second", "457635", 12, 170.0, Instant.ofEpochSecond(30)),
    new Goods("third", "789054", 145, 170.0, Instant.ofEpochSecond(3000)),
    new Goods("fourth", "124677", 123, 170.0, Instant.ofEpochSecond(125)),
    new Goods("fifth", "678547", 16, 170.0, Instant.ofEpochSecond(800)),
    new Goods("sixth", "234587", 18, 170.0, Instant.ofEpochSecond(7)),
    new Goods("seven", "123567", 18, 170.0, Instant.ofEpochSecond(6)),
    new Goods("eight", "789557", 15, 170.0, Instant.ofEpochSecond(1000)),
    new Goods("ninth", "675435", 1456, 170.0, Instant.ofEpochSecond(786)),
    new Goods("tenth", "457776", 6, 170.0, Instant.ofEpochSecond(786))));

        GoodsWithLambda goodsWithLambda = new GoodsWithLambda();
        goodsWithLambda.setGoods(list);
       // list = goodsWithLambda.sortByName();
        //list = goodsWithLambda.sortByNumber();
        //list = goodsWithLambda.sortByPartNumber();
        //list = goodsWithLambda.сountLess(17);
        list = goodsWithLambda.сountBetween(5,100);


       list.forEach(System.out::println);
    }
}


