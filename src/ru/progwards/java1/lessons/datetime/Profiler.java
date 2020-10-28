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
   static List<StatisticInfo> list1 = new ArrayList<>();


/*войти в профилировочную секцию, замерить время входа.*/
    public static void enterSection(String name){
        profiler.insEnter = Instant.now();
    }

/*выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и
выходом в миллисекундах.*/
    public static void exitSection(String name){
        profiler.insExit = Instant.now();
        StatisticInfo statisticInfo = new StatisticInfo();
        statisticInfo.sectionName = name;
        long timeWorkSection = Duration.between(profiler.insEnter, profiler.insExit).toMillis();
         method(statisticInfo);
        System.out.println(timeWorkSection);
    }

    public static List<StatisticInfo> getStatisticInfo(){ //это пока чтоб не путался
        return list1;
    }

    public static void method(StatisticInfo statisticInfo){
        if (list1.isEmpty()) list1.add(statisticInfo);
        else if (list1.contains(statisticInfo.sectionName)) {method1(statisticInfo); method2(statisticInfo);}
    }

    public static void method1(StatisticInfo st){
        for (int i = 0; i < list1.size(); i++){
            if (list1.get(i).sectionName.equals(st.sectionName)) {list1.get(i).count++;
                list1.get(i).fullTime = st.fullTime + list1.get(i).fullTime;
            }
        }
    }

    public static void method2(StatisticInfo stat){
        for (int i = 0; i < list1.size(); i++){
            if (stat.timeOfEnter.isAfter(list1.get(i).timeOfEnter)
                    &&stat.timeOfEnter.isBefore(list1.get(i).timeOfEnter)) list1.get(i).selfTime = list1.get(i).selfTime - stat.selfTime;

        }
    }

    public static void main(String[] args) {
        List<Integer> listInt = new ArrayList<>();
        enterSection("1");
        for (int i = 0; i < 1000000; i++){
            listInt.add(i);
        }
        exitSection("1");
        getStatisticInfo();




    }

}
