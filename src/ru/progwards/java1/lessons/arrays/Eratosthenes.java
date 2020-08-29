package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;
import java.util.Date;

public class Eratosthenes {

    private static boolean[] sieve;

    public Eratosthenes(int N){
        sieve = new boolean[N];
        for (int i = 0; i < sieve.length; i++){
            sieve[i] = true;
        }
        sift();
    }

    private void sift() {
        for (int i = 2; i < sieve.length; i++) {
            if (!sieve[i]) continue;
            for (int j = i; j < sieve.length; j++) {
               double k = (double) j * i;//пришлось к double тк при N = 20000000; и n = 19999999; вылетало исключение
                if (k >= sieve.length) break;
                else sieve[(int) k] = false;
            }
        }
    }// 0мс при равных условиях N = 20000000; и n = 19999999; АБСОЛЮТНЫЙ ПОБЕДИТЕЛЬ.

    /*private void sift(){
        for (int i = 2; i < sieve.length; i++) {
            for (int j = 2; j < sieve.length;j++) {
                //i- это шаг, j- составное число
                int k = j * i;
                if (k >= sieve.length) break;
                else sieve[k] = false;
                //прт N = 20000000; и n = 19999999; время выполнения = 1460 мс на моём ноутбуке
            }
        }
    }

     */
/* //метод с шагом чере сложение
private void sift(){
            for (int i = 2; i < sieve.length; i++) {
                for (int j = i; j < sieve.length;) {
                   //i- это шаг, j- составное число
                    j += i;
                    if (j >= sieve.length) break;
                   else sieve[j] = false;
                    //прт N = 20000000; и n = 19999999; время выполнения = 1486 мс на моём ноутбуке
                }
            }
        }//ВЫВОД НЕМНОГО МЕДЛЕННЕЕ, но рабочий

// метод через деление без остатка, самый медленный, подходит для небольших значений.
            private void sift(){
        for (int i = 2; i < sieve.length; i++){
            for (int j = 3; j < sieve.length; j++){
                if (j % i == 0 && !(i==j))sieve[j] = false;

            }
        }
    } //ДАЖЕ НЕ ДОЖДАЛСЯ, правильный, но НАМНОГО МЕДЛЕННЕЕ.
    */
    public  boolean isSimple(int n){


        return sieve[n];
    }

    public static void main(String[] args) {
        Eratosthenes era = new Eratosthenes(20000000);
        Date date1 = new Date();
        System.out.println(era.isSimple(19999999));
        Date date2 = new Date();
        System.out.println(date2.getTime()-date1.getTime());

    }
}
