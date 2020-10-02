package ru.progwards.java1.lessons.collections;

import java.util.*;


public class Finder {
    /*очень много раз перебираю список. мне не нраввится*/

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
        ArrayList<Integer> listForSum = new ArrayList<>(numbers);
        int TimesOfCycle = listForSum.size();
        for (int i = 1; i < TimesOfCycle; i++){
            listForSum.set(i - 1, listForSum.get(i-1) + listForSum.get(i));
        }
        int valueMin = listForSum.get(0);
        for (int i = 0; i < listForSum.size()-1; i++){
            if (listForSum.get(i) < valueMin) valueMin = listForSum.get(i);
        }
        Set<Integer> setForMinIndex = new HashSet<>();//для того, чтобы один и тотже индекс не повторялся.(например не 2,3,3,4, а 2,3,4)
        for (int i = 0; i < listForSum.size(); i++){
            if (listForSum.get(i) == valueMin) {setForMinIndex.add(i); setForMinIndex.add(i+1);}
        }
        List<Integer> result = new ArrayList<>(); //создал, тк set в произвольном порядке, а компилятор требовал в сортированом
        for (int a : setForMinIndex) {
            result.add(a);
        }
        Collections.sort(result);
        return  result;
    }


/*найти локальные максимумы - числа, которые больше соседа справа и слева. Первый и последний элемент
коллекции не может являться локальным  максимумом, вернуть коллекцию, содержащую значения этих максимумов
 */
    public static Collection<Integer> findLocalMax(Collection numbers){
        ArrayList<Integer> list = new ArrayList<>(numbers);
        List<Integer> listMaxValue = new ArrayList<>();
        for (int i = 1; i < list.size() - 1; i++){
            if (list.get(i-1) < list.get(i) && list.get(i) > list.get(i+1)){
            listMaxValue.add(list.get(i));}
        }
        return listMaxValue;
    }

    //первый вариант решения. ушел вроде бы от двойного цикла, но это не так. ниже более изящный вариант по моему мнению
    public static boolean findSequence1(Collection<Integer> numbers){
        Integer[] arrayFromCollection = new Integer[numbers.size()];
        numbers.toArray(arrayFromCollection);
        int j = 1;
        for (int i = 0; i < arrayFromCollection.length; i++){
            if(j > arrayFromCollection.length) break;
            if (j == arrayFromCollection[i]) {j++; i = -1; continue;}
            if (i == arrayFromCollection.length-1) return false;
        }
           return true;
    }
//другой вариант решения данной задачи
    public static boolean findSequence(Collection<Integer> numbers){
        Set<Integer> set = new HashSet<>(numbers);
        if (set.isEmpty() || set.size() < numbers.size()) return false; //для этой проверки я и создавал именно Set
        else
        for (int i = 1; i <= set.size(); i++){
            if (!set.contains(i)) return false;
        }
        return true;
    }
    /*найдите максимальное количество повторяющихся подряд элементов. Результат вернуть в виде строки
     <элемент>:<количество>, например Василий:5. При равенстве максимального количества у разных повторяющихся
     элементов, вернуть результат для элемента, повторяющаяся последовательность которого началась с наименьшего индекса.
*/
    public static String findSimilar(Collection<String> names){
        List<String> list = new ArrayList(names);
        StringBuilder result = new StringBuilder();
        int index = 0;
        int count = 1;
        int countMax = 0;
        for (int i = 1; i < list.size(); i++){
             if (list.get(i).equals(list.get(i-1))) count++;
             else count = 1;
             if (count > countMax) {countMax = count; index = i;
             result.replace(0,result.length(),list.get(i-1) + ":" + countMax);}
        }
        return result.toString();
    }


    public static void main(String[] args) {
        Collection<Integer> numbers = new ArrayList<>();
        numbers.add(67);
        numbers.add(64);
        numbers.add(-86);
        numbers.add(80);
        numbers.add(12);
        numbers.add(74);
        numbers.add(71);
        numbers.add(-19);
        numbers.add(-21);
        numbers.add(-55);
        numbers.add(20);
        numbers.add(84);
        numbers.add(96);
        numbers.add(52);
        numbers.add(-13);
        numbers.add(-22);
        numbers.add(-63);
        System.out.println(findMinSumPair(numbers).toString());//задача 2.1
        numbers.add(3);
        numbers.add(4);
        numbers.add(2);
        numbers.add(5);
        numbers.add(1);
        System.out.println(findLocalMax(numbers).toString());//задача 2.2
        System.out.print("findSequence должно быть false: ");//задача 2.3
        System.out.println(findSequence(numbers));
        numbers.clear();
        numbers.add(4);
        numbers.add(2);
        numbers.add(3);
        numbers.add(1);
        numbers.add(5);
        System.out.print("findSequence должно быть true: ");
        System.out.println(findSequence(numbers));
        ArrayList list = new ArrayList();
        list.add("Василий");
        list.add("Василий");
        list.add("Васили");
        list.add("владимир");
        list.add("владимир");
        list.add("владимир");
        list.add("сергей");
        list.add("сергей");
        list.add("сергей");
        list.add("asd");
        list.add("asd");
        list.add("zxc");
        list.add("qwe");
        list.add("zxc");
        System.out.println(findSimilar(list));
    }
}
