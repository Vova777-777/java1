package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Profiler {

    static Instant insEnter ;
    static Instant insExit ;
    long timeWorkSection ;
    public static List<StatisticInfo> list = new ArrayList<>();
    static Profiler profiler = new Profiler();
   static List<StatisticInfo> list1 = new ArrayList<>();

   static String  name1;

/*войти в профилировочную секцию, замерить время входа.*/
    public static void enterSection(String name){
        if (insEnter==null) {name1= name;
        insEnter = Instant.now();}
        else return;
    }

/*выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и
выходом в миллисекундах.*/
    public static void exitSection(String name){
        if (!(name1.equals(name))) return;
        insExit = Instant.now();

        long timeWorkSection = Duration.between(insEnter, insExit).toMillis();
        StatisticInfo statisticInfo = new StatisticInfo();
        statisticInfo.sectionName = name;
        statisticInfo.timeOfEnter = insEnter;
        statisticInfo.fullTime = (int) timeWorkSection;
        statisticInfo.selfTime = statisticInfo.fullTime;
        statisticInfo.count++;
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
        enterSection("2");
        exitSection("2");
        exitSection("1");
        System.out.println(getStatisticInfo().get(0).fullTime);
        System.out.println(getStatisticInfo().get(0).selfTime);
        System.out.println(getStatisticInfo().get(0).count);
        System.out.println(getStatisticInfo().get(1).fullTime);
        System.out.println(getStatisticInfo().get(1).selfTime);
        System.out.println(getStatisticInfo().get(1).count);



    }

}
