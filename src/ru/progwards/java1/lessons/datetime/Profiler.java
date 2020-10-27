package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Profiler {

    Instant insEnter = Instant.now();
    Instant insExit = Instant.now();
    long timeWorkSection = 0;
    public static List<StatisticInfo> list = new ArrayList<>();
    static Profiler profiler = new Profiler();



/*войти в профилировочную секцию, замерить время входа.*/
    public static void enterSection(String name){
        profiler.insEnter = Instant.now();
    }

/*выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и
выходом в миллисекундах.*/
    public static void exitSection(String name){
        profiler.insExit = Instant.now();
        long timeWorkSection = Duration.between(profiler.insEnter, profiler.insExit).toMillis();
        System.out.println(timeWorkSection);
    }

    public static List<StatisticInfo> getStatisticInfo(){ //это пока чтоб не путался
        return list;
    }




    public static void main(String[] args) {
        enterSection("1");
        exitSection("1");



    }

}
