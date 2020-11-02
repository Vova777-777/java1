package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Profiler {

    static Instant insEnter ;
    static Instant insExit ;
   static List<StatisticInfo> list1 = new ArrayList<>();
   static List<StatisticInfo> list2 = new ArrayList<>();



  static ArrayDeque<StatisticInfo> stack = new ArrayDeque<>();
/*войти в профилировочную секцию, замерить время входа.*/
    public static void enterSection(String name){
        insEnter = Instant.now();
        stack.push(createStaticInfo(name));
    }

    public static StatisticInfo createStaticInfo(String name){
        StatisticInfo statisticInfo = new StatisticInfo();
        statisticInfo.sectionName = name;
        statisticInfo.timeOfEnter = insEnter;
        statisticInfo.count++;
        return statisticInfo;
    }

/*выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и
выходом в миллисекундах.*/
    public static void exitSection(String name){
        StatisticInfo st = stack.pop();
        insExit = Instant.now();
        st.timeOfExit = insExit;
        long timeWorkSection = Duration.between(st.timeOfEnter, st.timeOfExit).toMillis();
        st.fullTime = (int) timeWorkSection;
        st.selfTime = st.fullTime;
        list1.add(st);
    }

        public static List<StatisticInfo> getStatisticInfo(){
        method1();
        method2();
        list2.sort(comparator);
        return list2;
    }

    public static void method1(){
        if (list1.size() == 1) list2.add(list1.get(0));
        for (int i = list1.size() - 1; i > 0; i--){
            for (int j = i - 1; j>= 0; j--)
            if (list1.get(i).timeOfEnter.isBefore(list1.get(j).timeOfEnter)
                    && list1.get(i).timeOfExit.isAfter(list1.get(j).timeOfExit))
                list1.get(i).selfTime = list1.get(i).selfTime - list1.get(j).selfTime;
        }
    }

    public static void method2(){
        for (int i = 0; i < list1.size() - 1; i ++){
            StatisticInfo statisticInfo = list1.get(i);
            if(list2.contains(statisticInfo)) continue;
            for (int j = i + 1; j < list1.size(); j++){
                if (statisticInfo.equals(list1.get(j))){
                    statisticInfo.fullTime = statisticInfo.fullTime + list1.get(j).fullTime;
                    statisticInfo.count = statisticInfo.count + list1.get(j).count;
                    statisticInfo.selfTime = statisticInfo.selfTime + list1.get(j).selfTime;

                }
            }
            list2.add(statisticInfo);
        }
    }



   static Comparator<StatisticInfo> comparator = new Comparator<StatisticInfo>() {
        @Override
        public int compare(StatisticInfo o1, StatisticInfo o2) {
            if (o1.equals(o2))
                return 0;
            if (o1.sectionName.compareTo(o2.sectionName) > 0) return 1;
            else return -1;
        }
    };



    public static void main(String[] args) throws InterruptedException {
        List<Integer> listInt = new ArrayList<>();
     /*   enterSection("1");
        Thread.sleep(100);

        enterSection("Process2");
        Thread.sleep(200);
       enterSection("Process3");
       Thread.sleep(100);
      exitSection("Process3");
        exitSection("Process2");

        enterSection("Process2");
        Thread.sleep(200);
       enterSection("Process3");
       Thread.sleep(100);
       exitSection("Process3");
        exitSection("Process2");

        exitSection("1");*/

        enterSection("1");
        Thread.sleep(100);
        exitSection("1");

        enterSection("1");
        Thread.sleep(100);
        exitSection("1");
        System.out.println(getStatisticInfo());
    }
}
