package ru.progwards.java1.lessons.arrays;

public class Eratosthenes {

    private static boolean[] sieve;

    public Eratosthenes(int N){
        sieve = new boolean[N];
        for (int i = 0; i < sieve.length; i++){
            sieve[i] = true;
        }
        sift();
    }

    private void sift(){
        for (int i = 2; i < sieve.length; i++){
           for (int j = 3; j < sieve.length; j++){
               if (j % i == 0 && !(i==j))sieve[j] = false;

           }
        }
    }

    public  boolean isSimple(int n){
        Eratosthenes number = new Eratosthenes(n+1);

        return sieve[n];
    }
}
