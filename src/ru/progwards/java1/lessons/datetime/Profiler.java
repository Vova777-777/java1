package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Profiler {

    static Instant insEnter ;
    static Instant insExit ;
   static List<StatisticInfo> listStatisticInfo = new ArrayList<>();
   static List<StatisticInfo> resultList = new ArrayList<>();



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
        listStatisticInfo.add(st);
    }

        public static List<StatisticInfo> getStatisticInfo(){
        if (listStatisticInfo.size() == 1) resultList.add(listStatisticInfo.get(0));
        getSelfTimeForEach();
        method2();
        resultList.sort(comparator);
        return resultList;
    }

    public static void getSelfTimeForEach(){

        for (int i = 0; i < listStatisticInfo.size(); i++){
            for (int j = 0; j < listStatisticInfo.size(); j++)
            if (listStatisticInfo.get(i).timeOfEnter.isBefore(listStatisticInfo.get(j).timeOfEnter)
                    && listStatisticInfo.get(i).timeOfExit.isAfter(listStatisticInfo.get(j).timeOfExit))
                listStatisticInfo.get(i).selfTime = listStatisticInfo.get(i).selfTime - listStatisticInfo.get(j).selfTime;
        }
    }

    public static void method2(){
        for (int i = 0; i < listStatisticInfo.size(); i ++){
            StatisticInfo statisticInfo = listStatisticInfo.get(i);
            if(resultList.contains(statisticInfo)) continue;
            for (int j = i + 1; j <= listStatisticInfo.size(); j++){
                if (j == listStatisticInfo.size()) break; //чтобы не было исключения по размеру списка
                if (statisticInfo.equals(listStatisticInfo.get(j))){
                    statisticInfo.fullTime = statisticInfo.fullTime + listStatisticInfo.get(j).fullTime;
                    statisticInfo.count = statisticInfo.count + listStatisticInfo.get(j).count;
                    statisticInfo.selfTime = statisticInfo.selfTime + listStatisticInfo.get(j).selfTime;
                }
            }
            resultList.add(statisticInfo);
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
        enterSection("1");
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

        exitSection("1");


        enterSection("1");
        Thread.sleep(100);
        exitSection("1");

        enterSection("1");
        Thread.sleep(100);
        exitSection("1");
        System.out.println(getStatisticInfo());
    }

}
