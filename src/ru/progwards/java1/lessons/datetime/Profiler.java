package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Profiler {

    static Instant insEnter ;
    static Instant insExit ;
   static List<StatisticInfo> list1 = new ArrayList<>();
   static List<StatisticInfo> list2 = new ArrayList<>();

public static int countEnter = 0;
public static int countExit = 0;

  static ArrayDeque<StatisticInfo> stack = new ArrayDeque<>();
/*войти в профилировочную секцию, замерить время входа.*/
    public static void enterSection(String name){
countEnter++;
        insEnter = Instant.now();
        stack.push(createStaticInfo(name));
    }

/*выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и
выходом в миллисекундах.*/
    public static void exitSection(String name){
        StatisticInfo st = stack.pop();
        insExit = Instant.now();
        st.timeOfExit = insExit;

        long timeWorkSection = Duration.between(st.timeOfEnter, insExit).toMillis();

         st.fullTime = (int) timeWorkSection;
         st.selfTime = st.fullTime;


            method1(st);
            countExit++;

            if (countExit == countEnter) method2();

    }

    public static StatisticInfo createStaticInfo(String name){
        StatisticInfo statisticInfo = new StatisticInfo();
        statisticInfo.sectionName = name;
        statisticInfo.timeOfEnter = insEnter;
        statisticInfo.timeOfExit = insExit;
        statisticInfo.fullTime = 0; //= (int) timeWorkSection;
        statisticInfo.selfTime = statisticInfo.fullTime;
        statisticInfo.count++;
        return statisticInfo;
    }



    public static void method1(StatisticInfo statisticInfo){
        if (list1.isEmpty()) list1.add(statisticInfo);
        else {list1.add(statisticInfo); method11();}
    }
    public static void method11(){
        int index = list1.size() - 1;
        int variable = list1.get(index).fullTime;
        for (int i = 0; i < list1.size(); i++){
            if (list1.get(index).timeOfEnter.isBefore(list1.get(i).timeOfEnter)
                    &&list1.get(index).timeOfExit.isAfter(list1.get(i).timeOfExit)){
                list1.get(index).selfTime = variable - list1.get(i).selfTime;
            variable = list1.get(index).selfTime;}
        }
    }

    public static void method2(){
        list2.add(list1.get(list1.size() - 1));
        for (int i =0; i < list1.size()-1; i++){
            StatisticInfo stat = list1.get(i);
            for (int j = i+1; j < list1.size(); j++){
                if (stat.equals(list1.get(j))){
                stat.fullTime = stat.fullTime + list1.get(j).fullTime;
                stat.count = stat.count + list1.get(j).count;
                stat.selfTime = stat.selfTime + list1.get(j).selfTime;
                }
            }
            method22(stat);
        }
    }
    public static void method22(StatisticInfo stat){
        if (list2.isEmpty()) list2.add(stat);
        else if (!checkContain(stat)) list2.add(stat);
    }
    public static boolean checkContain(StatisticInfo stat){
        for (int i = 0; i < list2.size(); i++){
            if (stat.sectionName.equals(list2.get(i).sectionName)) return true;
        }
        return false;
    }

    public static List<StatisticInfo> getStatisticInfo(){ //это пока чтоб не путался
        Collections.sort(list2, new Comparator<StatisticInfo>() {
            @Override
            public int compare(StatisticInfo o1, StatisticInfo o2) {
                o1.equals(o2);
                return 0;
            }
        });
        return list2;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> listInt = new ArrayList<>();
        enterSection("1");
        Thread.sleep(300);

        enterSection("2");
        Thread.sleep(200);

        enterSection("3");
        Thread.sleep(100);
        exitSection("3");

        exitSection("2");

        enterSection("2");
        Thread.sleep(200);

        enterSection("3");
        Thread.sleep(100);
        exitSection("3");

        exitSection("2");

        enterSection("2");
        Thread.sleep(200);

        enterSection("3");
        Thread.sleep(100);
        exitSection("3");

        exitSection("2");
        exitSection("1");


        System.out.println(getStatisticInfo());

    }

}
