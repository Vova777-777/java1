package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
/*1.1 Метод public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) - объединение множеств*/
       public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
           Set<Integer> setUnion = new HashSet<>(set1);
           setUnion.addAll(set2);
             return setUnion;
        }

//1.2 Метод public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) - пересечение множеств;
        public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
           Set<Integer> setIntersection = new HashSet<>(set1);
            setIntersection.retainAll(set2);
            return setIntersection;
        }

//1.3 Метод public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) - разница множеств;
        public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
           Set<Integer> setDifference = new HashSet<>(set1);
            setDifference.removeAll(set2);
           return setDifference;
        }
//1.4 Метод public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) - симметрическая разница
        public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
           Set setInteresection = new HashSet(set1);
           Set setSymDifference = new HashSet(set2);
            setInteresection.retainAll(set2);
            setSymDifference.removeAll(setInteresection);
            setSymDifference.addAll(difference(set1,set2));//
           return setSymDifference;
        }

    public static void main(String[] args) {
        //1.1
        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();
        set1 = Set.of(0,1,2,5,7,8,9,10);
        set2 = Set.of(0,1,3,4,5,6,7,8,9);
        System.out.println(union(set1,set2));
        //1.2
        System.out.println(intersection(set1, set2));
        //1.3
        System.out.println(difference(set1,set2));
        //1.4
        System.out.println(symDifference(set1,set2));//2,3,4,6,10
    }
}
