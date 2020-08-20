package ru.progwards.java1.lessons.compare_if_cycles;
/*является ли трегольник
       золотым?*/
import java.util.ArrayList;

public class CyclesGoldenFibo {
    public static void main(String[] args) {
for (int i = 1; i <= 15; i++) {//выводим на консоль 15 чисел фибоначчи
    System.out.println(fiboNumber(i));
}


for ( int i = 1; i <= 100; i++){// определяем сторона до 100 золотого треугольника и выводим их на консоль
    int a = i;
    int b;
    int c;
    b = a;
    for (int j = 1; j <= 100; j++){
        c = j;
        if (isGoldenTriangle(a, b, c) == true) System.out.println(a + " " + b + " " + c);
    }


    }
}



    public static boolean containsDigit(int number, int digit){
      String strNumber = String.valueOf(number);
      String strDigit = String.valueOf(digit);
      if (strNumber.contains(strDigit)) return true;
      else return false;
    }

    public static int fiboNumber(int n){
        ArrayList<Integer> listFibo = new ArrayList<>();
        listFibo.add(1);
        listFibo.add(1);
        for (int i = 2; i <= n; i ++){
            listFibo.add(listFibo.get(i - 2) + listFibo.get(i - 1));
        }
        return listFibo.get(n - 1);
    }
    public static boolean isGoldenTriangle(int a, int b, int c){
        double a1 = a;
        double b1 = b;
        double c1 = c;
       boolean attempt1 = (a == b && (a1/c1) > 1.61703 && (a1/c1) < 1.61903);
       boolean attempt2 = (a == c && (a1/b1) > 1.61703 && (a1/b1) < 1.61903);
       boolean attempt3 = (b == c && (b1/a1) > 1.61703 && (b1/a1) < 1.61903);
       if (attempt1) return attempt1;
       else if (attempt2) return attempt2;
       else if (attempt3) return attempt3;
       else return false;
    }
}
