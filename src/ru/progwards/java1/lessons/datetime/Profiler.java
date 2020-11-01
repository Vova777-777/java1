package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Profiler {

    static Instant insEnter ;
    static Instant insExit ;
   static List<StatisticInfo> list1 = new ArrayList<>();
   static List<StatisticInfo> list2 = new ArrayList<>();

  public static int count777 = 0;


public static int countExit = 0;

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
        statisticInfo.timeOfExit = insExit;
        statisticInfo.fullTime = 0; //= (int) timeWorkSection;
        statisticInfo.selfTime = statisticInfo.fullTime;
        statisticInfo.count++;
        return statisticInfo;
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


    }

    public static void method1(StatisticInfo statisticInfo){
        if (list1.isEmpty()) list1.add(statisticInfo);
        else method111(statisticInfo);
    }


        public static void method111(StatisticInfo statisticInfo){
            for (int i = 0; i < list1.size(); i++) {
                if (statisticInfo.timeOfEnter.isBefore(list1.get(i).timeOfEnter)
                        && statisticInfo.timeOfExit.isAfter(list1.get(i).timeOfExit))
                    statisticInfo.selfTime = statisticInfo.selfTime - list1.get(i).selfTime;

                if (statisticInfo.equals(list1.get(i))){
                    statisticInfo.fullTime = statisticInfo.fullTime + list1.get(i).fullTime;
                    statisticInfo.count = statisticInfo.count + list1.get(i).count;
                    statisticInfo.selfTime = statisticInfo.selfTime + list1.get(i).selfTime;
                }

                if (statisticInfo.equals(list1.get(i))) {list1.set(i, statisticInfo); count777 = 1;}
            }
            if (count777 == 0)list1.add(statisticInfo);
            count777 = 0;
        }









    public static List<StatisticInfo> getStatisticInfo(){ //это пока чтоб не путался
        Collections.sort(list2, new Comparator<StatisticInfo>() {
            int result;
            @Override
            public int compare(StatisticInfo o1, StatisticInfo o2) {
              if  (o1.sectionName.equals(o2.sectionName)) result = 0;
              else if (o1.sectionName.compareTo(o2.sectionName) == 1) result = 1;
              else if (o2.sectionName.compareTo(o1.sectionName) == -1) result = -1;
              return result;
            }
        });
        return list1;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> listInt = new ArrayList<>();
        enterSection("1");
        Thread.sleep(1);
        enterSection("2");
        Thread.sleep(2);
        enterSection("3");
        Thread.sleep(1);
        exitSection("3");
        exitSection("2");
        exitSection("1");


        enterSection("1");
        Thread.sleep(100);
        exitSection("1");







        System.out.println(getStatisticInfo());

    }

}
