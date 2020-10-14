package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort777 {

    private static class Sort777{
            long time;
            String method;

            Sort777(long time, String method){
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

            long timeOfMySort = timeOfSort(SortMethods.MYSORT).time;
            long timeOfMinSort = timeOfSort(SortMethods.MINSORT).time;
            long timeOfCollSort = timeOfSort(SortMethods.COLLSORT).time;
            Sort777 mySort = new Sort777(timeOfMySort, "mySort");
            Sort777 minSort = new Sort777(timeOfMinSort, "minSort");
            Sort777 collSort = new Sort777(timeOfCollSort, "collSort");

            Comparator<Sort777> comparator1 = new Comparator<Sort777>() {
                @Override
                public int compare(Sort777 o1, Sort777 o2) {
                    if (o1.time > o2.time) return 1;
                    if (o2.time > o1.time) return -1;
                    else return o1.method.compareTo(o2.method);

                }
            };
            List<Sort777> listSort = new ArrayList<>();
            Collections.addAll(listSort, mySort, minSort, collSort);
            Collections.sort(listSort,comparator1);
            List<String> result = new ArrayList<>();
            for (int i = 0; i < listSort.size(); i++){
                result.add(listSort.get(i).toString());
            }
            return result;
        }

        public static Sort777 timeOfSort (SortMethods method){
            List<Integer> list = new ArrayList<>();
            for (int i = 500; i >= 0; i--){
                list.add(i);
            }
            Date dateStartOfSort777Start = new Date();
            switch (method){
                case MYSORT: mySort(list);
                case MINSORT: minSort(list);
                case COLLSORT: collSort(list);
            }
            Date dateStartOfSort777Finish = new Date();
            long timeOfSort777 = dateStartOfSort777Finish.getTime() - dateStartOfSort777Start.getTime();

            return new Sort777(timeOfSort777, method.toString());
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
