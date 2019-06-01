package com.john.px500.util;

import android.os.Looper;

import com.john.px500.BuildConfig;

public class Asserts {
    /**
     * reports an assertion failure. If the build in debug the report is in the form of a system crash
     * (silly dev, dont do that). If it is production then report to some kind of health check system
     * like firebase or an analytics system
     *
     * @param condition    the condition that should be true
     * @param errorMessage the error message to include in the event that we want to force a crash
     */
    public static void thatTrue(boolean condition, String errorMessage) {
        if (condition) {
            return; // we are all good.
        }
        if (BuildConfig.DEBUG) {
            throw new IllegalStateException(errorMessage);
        } else {
            // Report an assertion failure as it may indicate an instability in the app
            // or an inconsistency between app and the back end systems
        }
    }

    public static void isNotOnMainThread() {
        thatTrue(Looper.myLooper() != Looper.getMainLooper(), "This should not be executed on the UI Thread");
    }

}
