package com.romainpiel.readerview.utils

import android.util.Log
import com.romainpiel.readerview.BuildConfig

object L {

    private val LOGGING_ENABLED = BuildConfig.DEBUG

    fun v(msg: String) {
        if (LOGGING_ENABLED) {
            Log.v(tag(), line() + msg)
        }
    }

    fun v(msg: String, tr: Throwable?) {
        if (LOGGING_ENABLED) {
            Log.v(tag(), line() + msg, tr)
        }
    }

    fun d(msg: String) {
        if (LOGGING_ENABLED) {
            Log.d(tag(), line() + msg)
        }
    }

    fun d(msg: String, tr: Throwable?) {
        if (LOGGING_ENABLED) {
            Log.d(tag(), line() + msg, tr)
        }
    }

    fun i(msg: String) {
        if (LOGGING_ENABLED) {
            Log.i(tag(), line() + msg)
        }
    }

    fun i(msg: String, tr: Throwable?) {
        if (LOGGING_ENABLED) {
            Log.i(tag(), line() + msg, tr)
        }
    }

    fun w(msg: String) {
        if (LOGGING_ENABLED) {
            Log.w(tag(), line() + msg)
        }
    }

    fun w(msg: String, tr: Throwable?) {
        if (LOGGING_ENABLED) {
            Log.w(tag(), line() + msg, tr)
        }
    }

    fun w(tr: Throwable?) {
        if (LOGGING_ENABLED) {
            Log.w(tag(), tr)
        }
    }

    fun e(msg: String) {
        if (LOGGING_ENABLED) {
            Log.e(tag(), line() + msg)
        }
    }

    fun e(msg: String, tr: Throwable?) {
        if (LOGGING_ENABLED) {
            Log.e(tag(), line() + msg, tr)
        }
    }

    private fun line(): String {
        val element = callingStackElement
        if (element != null) {
            return "${element.lineNumber} : "
        }
        return ""
    }

    private fun tag(): String {
        val element = callingStackElement
        if (element != null) {
            return element.className
        }
        return ""
    }

    private val callingStackElement: StackTraceElement?
        get() {
            val stackTrace = Thread.currentThread().stackTrace
            if (stackTrace.size > 5) {
                return stackTrace[5]
            } else {
                return null
            }
        }
}
