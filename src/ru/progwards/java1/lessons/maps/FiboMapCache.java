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



    public static void main(String[] args) {

    }

}
