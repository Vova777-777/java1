package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Profiler {

    private static LinkedList<StatisticInfo> statisticInfos = new LinkedList<>();

    private static ArrayDeque<StatisticInfo> stack = new ArrayDeque<>();

    //войти в профилировочную секцию, замерить время входа./
    public static void enterSection(String name) {
        StatisticInfo staticInfo = createStatisticInfo(name);
        stack.push(staticInfo);
        statisticInfos.add(staticInfo);
    }

    private static StatisticInfo createStatisticInfo(String name) {
        StatisticInfo statisticInfo = new StatisticInfo();
        statisticInfo.sectionName = name;
        statisticInfo.timeOfEnter = Instant.now();
        statisticInfo.count++;
        return statisticInfo;
    }

    /*выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и
    выходом в миллисекундах.*/
    public static void exitSection(String name) {
        StatisticInfo si = stack.pop();
        int indexOfSt = statisticInfos.lastIndexOf(si);
        Instant insExit = Instant.now();
        si.timeOfExit = insExit;
        long timeWorkSection = Duration.between(si.timeOfEnter, insExit).toMillis();
        si.fullTime = (int) timeWorkSection;

        if (indexOfSt == statisticInfos.size() - 1) {
            si.selfTime = si.fullTime;
        } else {
            StatisticInfo childSi = statisticInfos.get(indexOfSt + 1);
            List<StatisticInfo> statisticInfosFiltered = filterBySectionName(Profiler.statisticInfos.subList(indexOfSt, statisticInfos.size()), childSi.sectionName);
            int fullTimeChild = (int) fullTimeBySection(statisticInfosFiltered);
            si.selfTime = si.fullTime - fullTimeChild;
        }
    }

    private static long fullTimeBySection(List<StatisticInfo> statisticInfosFiltered) {
        long fullTime = 0;
        for (StatisticInfo si: statisticInfosFiltered) {
            fullTime = fullTime + si.fullTime;
        }
        return fullTime;
    }


    public static List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> commonStatistic = new ArrayList<>();
        statisticInfos.sort(comparator);

        Set<String> uniqueNames = getUniqueStatisticInfoNames(statisticInfos);
        for (String uniqueName : uniqueNames) {
            List<StatisticInfo> statisticInfosFilteredByName = filterBySectionName(Profiler.statisticInfos, uniqueName);
            if(statisticInfosFilteredByName.size() == 1) {
                commonStatistic.add(statisticInfosFilteredByName.get(0));
            }else {
                StatisticInfo reducedStatisticInfo = statisticInfosFilteredByName.get(0);
                for (int i = 1; i < statisticInfosFilteredByName.size(); i++) {
                    reducedStatisticInfo = reducedStatisticInfo.plus(statisticInfosFilteredByName.get(i));
                }
                commonStatistic.add(reducedStatisticInfo);
            }
        }
        commonStatistic.sort(comparator);
        return commonStatistic;
    }

    public static List<StatisticInfo> filterBySectionName(List<StatisticInfo> statisticInfos, String sectionName) {
        List<StatisticInfo> filteredByName = new ArrayList<>();
        for (StatisticInfo statisticInfo : statisticInfos) {
            if (statisticInfo.sectionName.equals(sectionName)) {
                filteredByName.add(statisticInfo);
            }
        }
        return filteredByName;
    }

    public static Set<String> getUniqueStatisticInfoNames(List<StatisticInfo> statisticInfos) {
        List<String> names = new LinkedList<>();
        for (StatisticInfo statisticInfo : statisticInfos) {
            names.add(statisticInfo.sectionName);
        }
        return new HashSet<>(names);
    }

    private static Comparator<StatisticInfo> comparator = (o1, o2) -> {
        if (o1.equals(o2))
            return 0;
        if (o1.sectionName.compareTo(o2.sectionName) > 0) return 1;
        else return -1;
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
