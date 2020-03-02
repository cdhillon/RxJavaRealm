package com.cdhillon.rxjavarealm;

import android.text.format.DateUtils;
import android.util.Log;

import org.junit.Test;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

    @Test
    public void testRx() {

        Observable<Integer> observable = Observable.just(1);
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.v(TAG, "value " + integer);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.e(TAG, "value" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.v(TAG, "onComplete");
            }
        });
    }
}
