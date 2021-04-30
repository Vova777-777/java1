package ru.progwards.java2.lessons.recursion;

import java.util.LinkedList;

public class HanoiTower {
    int size;
    int pos;
    int valueForFinishPrint;
    Integer indexForPrint;
    boolean on;


    public HanoiTower(int size, int pos){
        this.size = size;
        this.pos = pos;
        valueForFinishPrint = size;
        indexForPrint = size - 1;
    }

    class Ring{
        String name;
        public Ring(String name){
            this.name = name;
        }
    }

    class Tower{
        int size;
        LinkedList<Ring> stack = new LinkedList<>();
        public Tower(int size){
            this.size = size;
        }
    }

    Tower tower0;
    Tower tower1;
    Tower tower2;
    Tower towerFrom;

//    3.2 Реализовать метод
//    public move(int from, int to), который переносит башню со штыря from на штырь to

    public void move(int from, int to){
         if (checkIsTower()) createFirstTowers(this.pos);
        towerFrom = needTowerFrom(from);
        if (this.size == 1) {
            if (on) print();
            onlyMoveNotRecursion(from, to);
        }else{this.size -= 1; move(from, changeTo(from, to, 0));
            if (on) print();
            onlyMoveNotRecursion(from, to);
            move(changeFrom(from, to, 0), to);
            this.size += 1;}
        if (this.valueForFinishPrint == this.size && on) print();
    }

    private Tower needTowerFrom(int from){
        switch (from){
            case 0: return tower0;
            case 1: return tower1;
            case 2: return tower2;
        }
        return null;
    }

    private int changeTo(int from, int to, int firstPossibleNumberTo){
        if (firstPossibleNumberTo != to && firstPossibleNumberTo != from) return firstPossibleNumberTo;
        else {firstPossibleNumberTo += 1; return changeTo(from, to, firstPossibleNumberTo);}
    }

    private int changeFrom(int from, int to, int firstPossibleNumberFrom){
        if (firstPossibleNumberFrom != from && firstPossibleNumberFrom != to) return firstPossibleNumberFrom;
        else {firstPossibleNumberFrom += 1; return changeTo(from, to, firstPossibleNumberFrom);}
    }

    private boolean checkIsTower(){
        return  (tower0 == null);
    }

    private void createFirstTowers(int from){
        if (from == 0){
            tower0 = new Tower(3);
            tower1 = new Tower(0);
            tower2 = new Tower(0);
        tower0.stack = getStackWithRings(this.size, new LinkedList<>());
        tower1.stack = new LinkedList<>();
        tower2.stack = new LinkedList<>();
        }
        else if (from == 1) {
            tower0 = new Tower(0);
            tower1 = new Tower(3);
            tower2 = new Tower(0);
            tower0.stack = new LinkedList<>();
            tower1.stack = getStackWithRings(this.size, new LinkedList<>());
            tower2.stack = new LinkedList<>();}
        else{tower0 = new Tower(0);
            tower1 = new Tower(0);
            tower2 = new Tower(3);
            tower0.stack = new LinkedList<>();
            tower1.stack = new LinkedList<>();
            tower2.stack = getStackWithRings(this.size, new LinkedList<>());}
    }

    private LinkedList<Ring> getStackWithRings(int size, LinkedList<Ring> stack){
        if (size == 0) return stack;
        String strRing = String.format("<%03d>", size);
        stack.addLast(new Ring(strRing));
        return getStackWithRings(size -1, stack);
    }

    private void onlyMoveNotRecursion(int from, int to){
        if (from == 0 && to == 1){
            tower0.size -= 1; tower1.size +=1; tower1.stack.addLast(tower0.stack.pollLast());}
        else if (from == 1 && to == 0){
            tower0.size += 1; tower1.size -=1; tower0.stack.addLast(tower1.stack.pollLast());}
        else if (from == 1 && to == 2){
            tower1.size -= 1; tower2.size +=1; tower2.stack.addLast(tower1.stack.pollLast());}
        else if (from == 2 && to == 1){
            tower2.size -= 1; tower1.size +=1; tower1.stack.addLast(tower2.stack.pollLast());}
        else if (from == 0 && to == 2){
            tower0.size -= 1; tower2.size +=1; tower2.stack.addLast(tower0.stack.pollLast());}
        else if (from == 2 && to == 0){
            tower2.size -= 1; tower0.size +=1; tower0.stack.addLast(tower2.stack.pollLast());}
    }





//    3.3 Реализовать метод
//    void print(), который выводит текущее состояние башни на консоль в формате
//
//    каждое кольцо 5 символов - 3 символа добитых слева нулями, края <>
//    пустой штырь - буква I (латинское И большое) - остальное пробелы
//    между краями колец - один пробел
//    высота всегда == size
//    основание - символ "=", 17 шт

//      private void print(){
//        String str = "%s %s %s%n";
//        for (int i = this.valueForFinishPrint - 1; i >= 0; i--){
//            String print = String.format(str, valuesOfStringForPrint(tower0.stack, i),
//            valuesOfStringForPrint(tower1.stack, i), valuesOfStringForPrint(tower2.stack, i));
//            System.out.print(print);
//        }
//          System.out.println("=================");
//
//      }

        private void print(){
            String str = "%s %s %s%n";
            String print = String.format(str, valuesOfCharForPrint(tower0.stack, indexForPrint),
            valuesOfCharForPrint(tower1.stack, indexForPrint), valuesOfCharForPrint(tower2.stack, indexForPrint));
            System.out.print(print);
            indexForPrint -= 1;
            if (indexForPrint == -1) {System.out.println("=================");
                indexForPrint = valueForFinishPrint -1; return;
            }
            print();
        } /*На мой взгляд с циклом в данном методе цикл нагляднее (решение в комментариях выше), пришлось так, потому
        что по заданию циклы запрещены, пришлось в конструкторе вводить переменную indexForPrint*/


    private String valuesOfCharForPrint(LinkedList<Ring> stack, int numberString){
        try{
         return stack.get(numberString).name;
        }catch (IndexOutOfBoundsException e){
            return "  I  ";
        }
      }

      //3.3 реализовать метод void setTrace(boolean on) который включает отладочную печать состояния игрового поля после
      //каждого шага алгоритма (метода move). В результате все промежуточные ходы должны быть отображены

    void setTrace(boolean on){
        this.on = on;
    }


    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower(3,0);
        hanoiTower.setTrace(true);
        hanoiTower.move(0,2);
        //String strRing = String.format("<%03d>", 444);
        //System.out.println(strRing);
    }
}
