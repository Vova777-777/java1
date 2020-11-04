package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.Objects;
public class StatisticInfo {
    public String sectionName;
    public int fullTime;
    public int selfTime;
    public int count;
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

