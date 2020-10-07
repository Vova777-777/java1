package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
/*1.1 Метод public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) - объединение множеств*/
       public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
             set1.addAll(set2);
             return set1;
        }

//1.2 Метод public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) - пересечение множеств;
        public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
            set1.retainAll(set2);
            return set1;
        }

//1.3 Метод public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) - разница множеств;
        public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
           set1.removeAll(set2);
           return set1;
        }
//1.4 Метод public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) - симметрическая разница;
        public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
           Set interesectionSet = new HashSet(set1);
           interesectionSet.retainAll(set2);
           set1.addAll(set2);
           set1.removeAll(interesectionSet);
           return set1;
        }

    public static void main(String[] args) {
        //1.1
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);
        Set set2 = new HashSet();
        set2.add(1);
        set2.add(12);
        set2.add(13);
        set2.add(14);
        set2.add(15);
        System.out.println(union(set1,set2));

        //1.2
        System.out.println(intersection(set1, set2));

        //1.3
        set1.add(777);
        System.out.println(difference(set1,set2));

        //1.4
        set1.clear();
        set2.clear();
        set1.add(3);
        set1.add(5);
        set1.add(6);
        set1.add(9);
        set1.add(10);
        set2.add(0);
        set2.add(1);
        set2.add(2);
        set2.add(4);
        set2.add(5);
        set2.add(8);
        set2.add(9);
        set2.add(10);
        System.out.println(symDifference(set1,set2));
    }
}
