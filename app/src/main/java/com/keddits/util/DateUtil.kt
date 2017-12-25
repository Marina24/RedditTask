package com.keddits.util

import android.text.format.DateUtils
import java.util.*

/**
 * Created by marinaracu on 04.12.2017.
 */
class DateUtil {
    companion object {
        fun convertTime(time: Int): String {
            val timeStamp: Long = time.toLong()
            val cal: Calendar = Calendar.getInstance()
            val timeZone: TimeZone = cal.timeZone
            val realTime: CharSequence = DateUtils.getRelativeTimeSpanString(
                    timeStamp * 1000,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS)
            return realTime.toString()
        }
    }
}