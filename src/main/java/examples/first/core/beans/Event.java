package examples.first.core.beans;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Event {

    private int id;
    private String msg;
    private final Date date;
    private final DateFormat df;

    static Calendar calendar = Calendar.getInstance();

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        this.id = new Random().nextInt();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static boolean isDay() {
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        return currentHour >= 8 && currentHour < 17;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
