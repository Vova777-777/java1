package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import ru.progwards.java1.lessons.compare_if_cycles.CyclesGoldenFibo;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache;
    private boolean cacheOn;

    public FiboMapCache(boolean cacheOn) {
        this.cacheOn = cacheOn;
        this.fiboCache = new HashMap<>();
    }


    public BigDecimal fiboNumber(int n) {
        if (fiboCache.containsKey(n)) return fiboCache.get(n);
        else {fiboCache.put(n, new BigDecimal("" + fiboNumber(n)));
        return fiboCache.get(n);}
    }

    public void clearCahe(){
        fiboCache = null;
    }

    /*1.5 Для проверки работы реализовать public static void test() - тест для расчета чисел Фибоначчи от n = 1 до 1000
включительно и замерить разницу во времени с on = true и on = false, результат вывести на экран в формате "fiboNumber
cacheOn=??? время выполнения ???" для cacheOn=true и cacheOn=false, вместо ??? вывести реальные значения в мсек.*/

    public static void test(){
        FiboMapCache fiboMapCache = new FiboMapCache(true);
        fiboMapCache.fiboCache.put(1,new BigDecimal(1));
        fiboMapCache.fiboCache.put(2,new BigDecimal(1));
        BigDecimal value;
        for (int i = 3; i <= 1000; i++){
            value = fiboMapCache.fiboCache.get(i - 2).add(fiboMapCache.fiboCache.get(i - 1));
            fiboMapCache.fiboCache.put(i, value);
        }
        Date dateCasheOnTrueStart = new Date();
        fiboMapCache.fiboNumber(1000);
        Date dateCasheOnTrueFinish = new Date();
        long timeCasheOnTrue = dateCasheOnTrueFinish.getTime() - dateCasheOnTrueStart.getTime();
        System.out.println("fiboNumber cacheOn=true время выполнения " + timeCasheOnTrue);

        Date dateCasheOnFalseStart = new Date();
        fiboNumberCasheOnFalse(1000);
        Date dateCasheOnFalseFinish = new Date();
        long timeCasheOnFalse = dateCasheOnFalseFinish.getTime() - dateCasheOnFalseStart.getTime();
        System.out.println("fiboNumber cacheOn=false время выполнения " + timeCasheOnFalse);
    }

    public static int fiboNumberCasheOnFalse(int n){
        int number = 0;
        int a = 1;
        int b = 1;
        if (n == 1 || n == 2) return a;
        for (int i = 3; i <= n; i++){
            number = a + b;
            a = b;
            b = number;
        }
        return number;
    }





    public static void main(String[] args) {
        test();

    }

}
