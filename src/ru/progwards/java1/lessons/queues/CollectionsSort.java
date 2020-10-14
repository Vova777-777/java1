package ru.progwards.java1.lessons.queues;

import java.util.*;
/*Сергей посмотрите пожалуйста ещё класс CollectionsSort777. Скажите что лучше. В этом классе более прямолинейно
* решил, а в 777 решил метод по подсчету времени работы метода вынести в отдельный метод, и сделать его более
* универсальным (расширяемым), но для этого мне требовалось передавать ф - цию в качестве параметра метода, а так нельзя
* как я понимаю. Поэтому создал дополнительно enum. Какие нибудь способы есть дя передачи метода в качестве параметра
* которые можно бы было здесь использовать? При этом сама задача по общему количеству строк осталась примерно такой же
* и читаемость на мой взгляд не улучшилась. Но возможно универсальность чуть повысилась. Так ли это? И если есть
* какие то способы передать метод как параметр для другого метода напишите. В курсе было что передавали с помощью
* анонимного класса, но здесь это не подходит, тк анонимный класс привязан к какомуто методу и там немного по другому
*  Или я ошибаюсь? и здесь можно это применить */
public class CollectionsSort {

    private static class Sort{
        long time;
        String method;

        Sort(long time, String method){
            this.time = time;
            this.method = method;
        }

            @Override
            public String toString() {
                return method;
            }
    }

    public static void mySort(Collection<Integer> data){
        List<Integer> list = new ArrayList<>(data);
        for ( int i = 0; i < list.size() - 1; i++){
            for (int j = i + 1; j < list.size(); j++){
                int k;
                if (list.get(i) > list.get(j))
                {k = list.get(i); Collections.swap(list,i,j); list.remove(j);list.add(j, k);}
            }
        }
        data.removeAll(list);
        data.addAll(list);
    }

    public static void minSort(Collection<Integer> data){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++){
            Integer min = Collections.min(data);
            data.remove(min);
            i--;
            list.add(min);
        }
        data.addAll(list);
    }

    public static void collSort(Collection<Integer> data){
        List<Integer> list = new ArrayList<>(data);
        Collections.sort(list);
        data.removeAll(list);
        data.addAll(list);
    }

    public static Collection<String> compareSort(){
        long timeOfMySort = timeOfSort().get(0);
        long timeOfMinSort = timeOfSort().get(1);
        long timeOfCollSort = timeOfSort().get(2);
        Sort mySort = new Sort(timeOfMySort, "mySort");
        Sort minSort = new Sort(timeOfMinSort, "minSort");
        Sort collSort = new Sort(timeOfCollSort, "collSort");

        Comparator<Sort> comparator1 = new Comparator<Sort>() {
            @Override
            public int compare(Sort o1, Sort o2) {
                if (o1.time > o2.time) return 1;
                if (o2.time > o1.time) return -1;
                else return o1.method.compareTo(o2.method);

            }
        };

        List<Sort> listSort = new ArrayList<>();
        Collections.addAll(listSort, mySort, minSort, collSort);
        Collections.sort(listSort,comparator1);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < listSort.size(); i++){
            result.add(listSort.get(i).toString());
        }
        return result;
    }

    public static List<Long> timeOfSort (){
        List<Integer> list = new ArrayList<>();
        for (int i = 500; i >= 0; i--){
            list.add(i);
        }
        Date dateStartOfMySort1 = new Date();
        mySort(list);
        Date dateStartOfMySort2 = new Date();
        long timeOfMySort = dateStartOfMySort2.getTime() - dateStartOfMySort1.getTime();
        Date dateStartOfMinSort1 = new Date();
        minSort(list);
        Date dateStartOfMinSort2 = new Date();
        long timeOfMinSort = dateStartOfMinSort2.getTime() - dateStartOfMinSort1.getTime();
        Date dateStartOfCollSort1 = new Date();
        collSort(list);
        Date dateStartOfCollSort2 = new Date();
        long timeOfCollSort = dateStartOfCollSort2.getTime() - dateStartOfCollSort1.getTime();
        List<Long> result = new ArrayList<>();
        Collections.addAll(result, timeOfMySort, timeOfMinSort, timeOfCollSort);
        return result;
    }

    public static void main(String[] args) {
        /*задача  1.1*/
        List<Integer> collection = new ArrayList<>();
        collection.add(11);
        collection.add(9);
        collection.add(5);
        collection.add(22);
        collection.add(33);
        collection.add(45);
        collection.add(1);
        System.out.println(collection);
        mySort(collection);
        System.out.println("Метод mySort: " + collection);

        /*задача 1.2*/
        Collections.shuffle(collection);
        minSort(collection);
        System.out.println("Метод minSort: " + collection);

        /*задача 1.3*/
        Collections.shuffle(collection);
        collSort(collection);
        System.out.println("Метод collSort: " + collection);

        /*задача 1.4*/
        System.out.println("Метод compareSort: " + compareSort());


    }
}
