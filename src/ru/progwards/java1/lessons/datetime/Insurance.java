package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
            case SHORT: start = ZonedDateTime.parse(strStart, ISO_LOCAL_DATE);
            break;
            case LONG: start = ZonedDateTime.parse(strStart, ISO_LOCAL_DATE_TIME);
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
        duration = Duration.between(expiration, start);
        System.out.println(duration.toDays());//////
    }

    public void setDuration(int months, int days, int hours){
        duration = Duration.ofSeconds(0);
        duration = Duration.between(start, start.plusMonths(months).plusDays(days).plusDays(hours));
        System.out.println(duration.toDays());

    }

/*SHORT - целое число миллисекунд (тип long)
LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает, что продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
FULL - стандартный формат Duration, который получается через toString()*/

    public void setDuration(String strDuration, FormatStyle style){
        duration = Duration.ofSeconds(0);
        switch (style){
            case SHORT: duration = Duration.ofSeconds(0);duration = duration.plusMillis(Long.valueOf(strDuration));
            break;
            case LONG:
                LocalDateTime ldt2 = LocalDateTime.parse(strDuration, ISO_LOCAL_DATE_TIME);
                LocalDateTime ldt1 = LocalDateTime.of(0000,01,01,00,00,00);
                duration = Duration.between(ldt1, ldt2);
                break;
            case FULL: duration = Duration.parse(strDuration);
            default:
                System.out.println("Введите в правильном формате");
                break;
        }
    }

    public boolean checkValid(ZonedDateTime dateTime){
        if (duration == null) return true;
        if (!dateTime.isAfter(start)) return false;
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
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime zdt1 = ZonedDateTime.of(2020,9,1,00,00,00,00,zoneId);
        ZonedDateTime zdt2 = ZonedDateTime.of(2020,10,6,00,00,00,00,zoneId);
        Insurance ins  = new Insurance(zdt1);
        //ins.setDuration(zdt2);


        //ins.setDuration(1,0,1);
        //System.out.println(ins.duration.toDays());

        //ins.setDuration("1000", FormatStyle.SHORT);
        //System.out.println(ins.duration);
        ins.setDuration("0000-06-03T00:00:25", FormatStyle.LONG);
        //System.out.println(ins.duration);

        System.out.println(ins.checkValid(zdt2));





    }
}
