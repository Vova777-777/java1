package ru.progwards.java1.lessons.queues;
import ru.progwards.java1.lessons.queues.StackCalc;
public class Calculate {
    /*3.7 public static double calculation1(), возвращающую результат вычисления следующей формулы:

2.2*(3+12.1), используя класс StackCalc
3.8 public static double calculation2(), возвращающую результат вычисления следующей формулы:

(737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)), используя класс StackCalc
*/
    public static double calculation1(){
        StackCalc stackCalc = new StackCalc();
        stackCalc.push(12.1);
        stackCalc.push(3);
        stackCalc.add();
        stackCalc.push(2.2);
        stackCalc.mul();
        return stackCalc.pop();
    }

    public static double calculation2(){
        StackCalc stackCalc = new StackCalc();
        stackCalc.push(13.001);
        stackCalc.push(9.2);
        stackCalc.sub();
        stackCalc.push(2);
        stackCalc.mul();
        stackCalc.push(87);
        stackCalc.add();
        stackCalc.push(19);
        stackCalc.push(3.33);
        stackCalc.sub();
        stackCalc.mul();
        stackCalc.push(24);
        stackCalc.push(737.22);
        stackCalc.add();
        stackCalc.push(55.6);
        stackCalc.push(12.1);
        stackCalc.sub();
        stackCalc.div();
        stackCalc.add();
        return stackCalc.pop();
    }


    public static void main(String[] args) {
        System.out.println(calculation1());
        System.out.println(2.2*(3+12.1));
        System.out.println(calculation2());
        System.out.println((737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)));
    }
}
