package com.rohan.java.concurrency.common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Utils {

    private Utils() {
    }

    public static void sleepInMillis(long sleepTimeInMillis) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTimeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static Date getFutureTime(Date initialTime, long millisToAdd) {

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(initialTime.getTime() + millisToAdd);

        return calendar.getTime();
    }

}
