package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Profiler {

    static Instant insEnter ;
    static Instant insExit ;
   static List<StatisticInfo> statisticInfoList = new ArrayList<>();
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
        statisticInfoList.add(st);
    }

        public static List<StatisticInfo> getStatisticInfo(){
        if (statisticInfoList.size() == 1) resultList.add(statisticInfoList.get(0));
        getSelfTimeForEach();
        method2();
        resultList.sort(comparator);
        return resultList;
    }

    public static void getSelfTimeForEach(){

        for (int i = 0; i < statisticInfoList.size(); i++){
            for (int j = 0; j < statisticInfoList.size(); j++)
            if (statisticInfoList.get(i).timeOfEnter.isBefore(statisticInfoList.get(j).timeOfEnter)
                    && statisticInfoList.get(i).timeOfExit.isAfter(statisticInfoList.get(j).timeOfExit))
                statisticInfoList.get(i).selfTime = statisticInfoList.get(i).selfTime - statisticInfoList.get(j).selfTime;
        }
    }

    public static void method2(){
        for (int i = 0; i < statisticInfoList.size(); i ++){
            StatisticInfo statisticInfo = statisticInfoList.get(i);
            if(resultList.contains(statisticInfo)) continue;
            for (int j = i + 1; j <= statisticInfoList.size(); j++){
                if (j == statisticInfoList.size()) break;
                if (statisticInfo.equals(statisticInfoList.get(j))){
                    statisticInfo.fullTime = statisticInfo.fullTime + statisticInfoList.get(j).fullTime;
                    statisticInfo.count = statisticInfo.count + statisticInfoList.get(j).count;
                    statisticInfo.selfTime = statisticInfo.selfTime + statisticInfoList.get(j).selfTime;

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

       // enterSection("Process2");
      //  Thread.sleep(200);
      // enterSection("Process3");
      // Thread.sleep(100);
       //exitSection("Process3");
       // exitSection("Process2");

        exitSection("1");

      //  enterSection("1");
      //  Thread.sleep(100);
      //  exitSection("1");

      //  enterSection("1");
        ///Thread.sleep(100);
        //exitSection("1");
        System.out.println(getStatisticInfo());
    }

}
