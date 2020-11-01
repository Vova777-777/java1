package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

 public class StatisticInfo {
    public String sectionName;
    public int fullTime;      //полное время выполнения секции в миллисекундах.
    public int selfTime;       /* чистое время выполнения секции в миллисекундах. Для вложенных секций,
                                 из времени выполнения внешней секции нужно вычесть времена выполнения
                                    вложенных секций*/
    public int count;            /*количество вызовов. В случае, если вызовов более одного, fullTime и
                               selfTime содержат суммарное время выполнения всех вызовов.*/

    public Instant timeOfEnter;
    public Instant timeOfExit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticInfo)) return false;
        StatisticInfo that = (StatisticInfo) o;
        return Objects.equals(sectionName, that.sectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionName);
    }

    @Override
    public String toString() {
        return "StatisticInfo{" +
                "sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                '}';
    }




}

