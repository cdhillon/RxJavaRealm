package com.cdhillon.rxjavarealm;

import android.text.format.DateUtils;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by cdhillon on 6/8/17.
 */

public class DateUtilTest extends RobolectricTest {
    static final String TAG = DateUtilTest.class.getSimpleName();
    @Test
    public void testDateTimeSpan() {
        long now = System.currentTimeMillis();

        long threeHoursPast = now - TimeUnit.HOURS.toMillis(3);
        long threeHoursFuture = now + TimeUnit.HOURS.toMillis(3);

        long threeDaysPast = now - TimeUnit.DAYS.toMillis(3);
        long threeDaysFuture = now + TimeUnit.DAYS.toMillis(3);

        System.out.println(DateUtils.getRelativeTimeSpanString(context, threeHoursFuture, true));
        System.out.println(DateUtils.getRelativeTimeSpanString(context, threeDaysFuture, false));

        //format date/time does not matter if we are not showing date/time. For example in below, we show elapsed days
        Logger.v(TAG, DateUtils.getRelativeTimeSpanString(threeDaysPast, now, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE).toString());
        Logger.v(TAG, DateUtils.getRelativeTimeSpanString(threeDaysPast, now, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_TIME).toString());

        Logger.v(TAG, DateUtils.getRelativeTimeSpanString(threeHoursPast, now, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE).toString());
        Logger.v(TAG, DateUtils.getRelativeTimeSpanString(threeHoursFuture, now, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_TIME).toString());

        Logger.v(TAG, DateUtils.getRelativeTimeSpanString(threeHoursPast, now, DateUtils.HOUR_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE).toString());
        Logger.v(TAG, DateUtils.getRelativeTimeSpanString(threeHoursFuture, now, DateUtils.HOUR_IN_MILLIS, DateUtils.FORMAT_SHOW_TIME).toString());

        Logger.v(TAG, DateUtils.getRelativeDateTimeString(context, threeDaysFuture, DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS,
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY).toString());

//        Logger.v(TAG, DateUtils.getRelativeTimeSpanString(threeDaysFuture, now, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_TIME).toString());
    }
}
