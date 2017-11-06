package xyz.rimon.adateswitcher.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by SAyEM on 6/11/17.
 */

public class DateUtil {

    public static DateFormat getMonthNameDateFormat(){
        return new SimpleDateFormat("MMMM", Locale.getDefault());
    }

    public static DateFormat getMonthDayDateFormat(){
        return new SimpleDateFormat("MMM dd", Locale.getDefault());
    }

}
