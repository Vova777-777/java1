package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache;

    public FiboMapCache(boolean cacheOn) {
        if (cacheOn = true) this.fiboCache = getFiboCache();
    }

    FiboMapCache fiboMapCache = new FiboMapCache(true);

    public Map<Integer, BigDecimal> getFiboCache() {
        return fiboCache;
    }

    public BigDecimal fiboNumber(int n) {

        BigDecimal value = new BigDecimal(n);
        if (fiboMapCache.getFiboCache().containsKey(n)) return fiboMapCache.fiboCache.remove(n);
        else {fiboMapCache.getFiboCache().put(n, value); return value;}
    }

    public void clearCahe(){
        fiboCache.clear();
    }

    public static void test(){

        ArrayList<Integer> listFibo = new ArrayList<>();
            fiboMapCache.fiboCache.put(1, new BigDecimal(1));
            listFibo.add(1);
            listFibo.add(1);
            for (int i = 2; i <= 1000; i ++){
                listFibo.add(listFibo.get(i - 2) + listFibo.get(i - 1));
                fiboMapCache.fiboCache.put(listFibo.get(listFibo.size()-1), new BigDecimal(listFibo.size()-1));
            }
        System.out.println(fiboMapCache1.fiboNumber(6));
    }

    public static void main(String[] args) {
test();
    }

}
