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

    Instant insEnter = new Profiler().insEnter;
    Instant insExit = new Profiler().insExit;
Profiler profiler = new Profiler();

    public void method(StatisticInfo statisticInfo){
        if (profiler.list.isEmpty()) profiler.list.add(statisticInfo);
        else if (profiler.list.contains(statisticInfo)) {method1(statisticInfo); method2(statisticInfo);}
    }

    public void method1(StatisticInfo st){
        for (int i = 0; i < profiler.list.size(); i++){
            if (profiler.list.get(i).sectionName.equals(st.sectionName)) {profiler.list.get(i).count++;
                profiler.list.get(i).fullTime = st.fullTime + profiler.list.get(i).fullTime;
                }
        }
    }

    public void method2(StatisticInfo stat){
        int a = -1;
        for (int i = 0; i < profiler.list.size(); i++){
            if (stat.insEnter.isAfter(profiler.list.get(i).insEnter)
                    &&stat.insEnter.isBefore(profiler.list.get(i).insExit)) profiler.list.get(i).selfTime = profiler.list.get(i).selfTime - stat.selfTime;

        }
    }
}

