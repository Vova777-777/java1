package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;

public class Insurance {

    public static enum FormatStyle {SHORT, LONG, FULL}
    private ZonedDateTime start;
    private Duration duration;
    DateTimeFormatter dtf = null;

    public Insurance(ZonedDateTime start){
        this.start = start;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart() {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style){
        switch (style){
            case SHORT: LocalDate ld = LocalDate.parse(strStart, ISO_LOCAL_DATE);
            start = ZonedDateTime.of(ld, LocalTime.of(00,00,00), ZoneId.systemDefault());
            break;
            case LONG: LocalDateTime localDateTime = LocalDateTime.parse(strStart, ISO_LOCAL_DATE_TIME);
                start = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            break;
            case FULL: start = ZonedDateTime.parse(strStart, ISO_ZONED_DATE_TIME);
            break;
            default:
                System.out.println("Введите в правильном формате");
                break;
        }
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration){
        duration = Duration.ofSeconds(0);
        duration = Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours){
        duration = Duration.ofSeconds(0);
        duration = Duration.between(start, start.plusMonths(months).plusDays(days).plusHours(hours));
        System.out.println(duration.toDays());

    }

    public void setDuration(String strDuration, FormatStyle style){
        duration = Duration.ofSeconds(0);
        switch (style){
            case SHORT: duration = Duration.ofSeconds(0);duration = duration.plusMillis(Long.valueOf(strDuration));
            break;
            case LONG:
                LocalDateTime ldt2 = LocalDateTime.parse(strDuration, ISO_LOCAL_DATE_TIME);
                LocalDateTime ldt1 = LocalDateTime.of(0000,01,01,00,00,00);
                duration = Duration.between(ldt1, ldt2.plusMonths(1));
                break;
            case FULL: duration = Duration.parse(strDuration);
            default:
                System.out.println("Введите в правильном формате");
                break;
        }
    }

    public boolean checkValid(ZonedDateTime dateTime){
        if (start.isAfter(ZonedDateTime.now())) return false;
        if (duration == null) return true;
        if (dateTime.isBefore(start)) return false;
        if (dateTime.isAfter(start.plus(duration))) return false;
        else return true;
    }

    @Override
    public String toString() {
        if (checkValid(ZonedDateTime.now()))
        return "Insurance issued on " + start + " is valid";
        else return "Insurance issued on " + start + " is not valid";
    }

    public static void main(String[] args) {
        ZonedDateTime start = ZonedDateTime.parse("2020-10-25T20:58:13.848532+03:00[Europe/Moscow]");
        Insurance ins = new Insurance(start);
        ins.setDuration(0, 0, 1);
        System.out.println(ins.toString());
    }
}
