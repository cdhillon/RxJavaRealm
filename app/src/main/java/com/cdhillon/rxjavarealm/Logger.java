package com.cdhillon.rxjavarealm;

import android.util.Log;

/**
 * Created by chetan on 4/20/17.
 */

public class Logger {

    static void v(String tag, String msg) {
        Log.v(getTag(tag), msg);
    }

    static void d(String tag, String msg) {
        Log.d(getTag(tag), msg);
    }

    static void e(String tag, String msg) {
        Log.e(getTag(tag), msg);
    }

    static void e(String tag, String msg, Throwable t) {
        Log.e(getTag(tag), msg, t);
    }

    static String getTag(String tag) {
        return tag + " [" + Thread.currentThread().getName() +"]";
    }
}
