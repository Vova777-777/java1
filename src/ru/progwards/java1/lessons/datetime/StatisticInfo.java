package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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









}

