package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class StatisticInfo {
    public String sectionName = "unknown";
    public int fullTime =(int) new Profiler().timeWorkSection;      //полное время выполнения секции в миллисекундах.
    public int selfTime = fullTime;       /* чистое время выполнения секции в миллисекундах. Для вложенных секций,
                                 из времени выполнения внешней секции нужно вычесть времена выполнения
                                    вложенных секций*/
    public int count;            /*количество вызовов. В случае, если вызовов более одного, fullTime и
                               selfTime содержат суммарное время выполнения всех вызовов.*/



    Profiler profiler = new Profiler();
    Instant insEnter = profiler.insEnter;
    Instant insExit = profiler.insExit;
List<StatisticInfo> list1 = new ArrayList<>();

    public void method(StatisticInfo statisticInfo){
        if (list1.isEmpty()) list1.add(statisticInfo);
        else if (list1.contains(statisticInfo)) {method1(statisticInfo); method2(statisticInfo);}
    }

    public void method1(StatisticInfo st){
        for (int i = 0; i < list1.size(); i++){
            if (list1.get(i).sectionName.equals(st.sectionName)) {list1.get(i).count++;
                list1.get(i).fullTime = st.fullTime + list1.get(i).fullTime;
                }
        }
    }

    public void method2(StatisticInfo stat){
        for (int i = 0; i < list1.size(); i++){
            if (stat.insEnter.isAfter(list1.get(i).insEnter)
                    &&stat.insEnter.isBefore(list1.get(i).insExit)) list1.get(i).selfTime = list1.get(i).selfTime - stat.selfTime;

        }
    }


}

