package com.reddittask.android.util;

import android.text.format.DateUtils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by marinaracu on 30.11.2017.
 */

public class DateUtil {

    public static String convertTime(int time) {
        long timestamp = time;
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        CharSequence relTime = DateUtils.getRelativeTimeSpanString(
                timestamp * 1000,
                System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS);

        return relTime.toString();
    }
}
