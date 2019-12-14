package com.roomapp.toolsloan.utality

import android.util.Log
import timber.log.Timber

class CrashReportingTree : Timber.Tree(){
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.INFO
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        // CrashAnaytics doSomething
        /*
        Crashlytics.log(priority, tag, message);
        if (t != null) {
            if (priority == Log.ERROR) {
                Crashlytics.logException(t);
            }
        }
        */
    }
}
