package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Profiler {

    static Instant insEnter ;
    static Instant insExit ;
   static List<StatisticInfo> list1 = new ArrayList<>();


  static ArrayDeque<StatisticInfo> stack = new ArrayDeque<>();
/*войти в профилировочную секцию, замерить время входа.*/
    public static void enterSection(String name){

        insEnter = Instant.now();
        stack.push(createStaticInfo(name));
    }

/*выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и
выходом в миллисекундах.*/
    public static void exitSection(String name){
        insExit = Instant.now();
        long timeWorkSection = Duration.between(insEnter, insExit).toMillis();
         StatisticInfo st = stack.pop();
         st.fullTime = (int) timeWorkSection;
         st.selfTime = st.fullTime;

            method(st);


    }

    public static StatisticInfo createStaticInfo(String name){
        StatisticInfo statisticInfo = new StatisticInfo();
        statisticInfo.sectionName = name;
        statisticInfo.timeOfEnter = insEnter;
        statisticInfo.fullTime = 0; //= (int) timeWorkSection;
        statisticInfo.selfTime = statisticInfo.fullTime;
        statisticInfo.count++;
        return statisticInfo;
    }

    public static List<StatisticInfo> getStatisticInfo(){ //это пока чтоб не путался
        Collections.sort(list1, new Comparator<StatisticInfo>() {
            @Override
            public int compare(StatisticInfo o1, StatisticInfo o2) {
                o1.sectionName.compareTo(o2.sectionName);
                return -1;
            }
        });
        return list1;
    }

    public static void method(StatisticInfo statisticInfo){
        if (list1.isEmpty()) list1.add(statisticInfo);
        else if (containListStInfo(list1, statisticInfo.sectionName)) {method1(statisticInfo); method2(statisticInfo);}
        else {list1.add(statisticInfo) ;method2(statisticInfo);}
    }

    public static boolean containListStInfo(List<StatisticInfo> list, String name){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).sectionName.equals(name)) return true;
        }
        return false;
    }

    public static void method1(StatisticInfo st){
        for (int i = 0; i < list1.size(); i++){
            if (list1.get(i).sectionName.equals(st.sectionName)) {list1.get(i).count++;
                list1.get(i).fullTime = st.fullTime + list1.get(i).fullTime;
                list1.get(i).selfTime = st.selfTime + list1.get(i).selfTime;
            }
        }
    }

    public static void method2(StatisticInfo stat){
        for (int i = 0; i < list1.size(); i++){
            if (stat.timeOfEnter.isBefore(list1.get(i).timeOfEnter)
                    &&stat.timeOfEnter.isAfter(list1.get(i).timeOfEnter))
            {stat.selfTime = stat.selfTime - list1.get(i).fullTime;
            list1.remove(i); list1.add(stat);}

        }
    }

    public static void main(String[] args) {
        List<Integer> listInt = new ArrayList<>();
        enterSection("1");
        for (int i = 0; i < 1000000; i++){
            listInt.add(i);
        }
        enterSection("2");
        for (int i = 0; i < 1000; i++){
            listInt.add(i);
        }
        exitSection("2");
        enterSection("2");
        for (int i = 0; i < 1000; i++){
            listInt.add(i);
        }
        exitSection("2");
        exitSection("1");
        System.out.println(getStatisticInfo().get(0).fullTime);
        System.out.println(getStatisticInfo().get(0).selfTime);
        System.out.println(getStatisticInfo().get(0).count);
        System.out.println(getStatisticInfo().get(1).fullTime);
        System.out.println(getStatisticInfo().get(1).selfTime);
        System.out.println(getStatisticInfo().get(1).count);
        System.out.println(getStatisticInfo());

    }

}
