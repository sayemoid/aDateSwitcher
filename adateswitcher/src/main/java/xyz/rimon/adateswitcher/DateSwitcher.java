package xyz.rimon.adateswitcher;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import xyz.rimon.adateswitcher.utils.DateUtil;

/**
 * Created by SAyEM on 6/11/17.
 */

public class DateSwitcher extends LinearLayout implements View.OnClickListener {

    private TextView txtDate;

    private Calendar calendar;

    private Type type = Type.MONTH;

    private OnDateChangeListener listener;


    public enum Type {
        MONTH, WEEK, FORNIGHT;
    }

    public enum DateRange {
        BOTTOM_DATE, TOP_DATE;
    }

    public DateSwitcher(Context context) {
        super(context);
        this.initView();
    }


    public DateSwitcher(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.date_switcher, this);

        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);
        this.txtDate = findViewById(R.id.txtDate);

        calendar = Calendar.getInstance();

        this.populateTextView(this.getDateRange());

        // Listen to buttons
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void populateTextView(Map<DateRange, Date> dateRange) {
        if (this.type.equals(Type.MONTH))
            this.txtDate.setText(DateUtil.getMonthNameDateFormat().format(dateRange.get(DateRange.BOTTOM_DATE)));
        else if (this.type.equals(Type.WEEK)) {
            Date topDate = dateRange.get(DateRange.TOP_DATE);
            Date bottomDate = dateRange.get(DateRange.BOTTOM_DATE);
            this.txtDate.setText(DateUtil.getMonthDayDateFormat().format(bottomDate) + " - " + DateUtil.getMonthDayDateFormat().format(topDate));
        } else if (this.type.equals(Type.FORNIGHT)) {
            Date topDate = dateRange.get(DateRange.TOP_DATE);
            Date bottomDate = dateRange.get(DateRange.BOTTOM_DATE);
            this.txtDate.setText(DateUtil.getMonthDayDateFormat().format(bottomDate) + " - " + DateUtil.getMonthDayDateFormat().format(topDate));
        }


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnNext) {
            if (this.type.equals(Type.MONTH))
                calendar.add(Calendar.MONTH, 1);
            else if (this.type.equals(Type.WEEK))
                calendar.add(Calendar.DAY_OF_MONTH, 7);
            else if (this.type.equals(Type.FORNIGHT))
                calendar.add(Calendar.DAY_OF_MONTH, 14);
        } else if (id == R.id.btnPrevious) {
            if (this.type.equals(Type.MONTH))
                calendar.add(Calendar.MONTH, -1);
            else if (this.type.equals(Type.WEEK))
                calendar.add(Calendar.DAY_OF_MONTH, -7);
            else if (this.type.equals(Type.FORNIGHT))
                calendar.add(Calendar.DAY_OF_MONTH, -14);
        }

        this.populateTextView(this.getDateRange());
        this.listener.onChange(this.getDateRange());
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        this.populateTextView(this.getDateRange());
    }

    public Map<DateRange, Date> getDateRange() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.calendar.getTime());
        Map<DateRange, Date> dateRangMap = new HashMap<>();
        if (this.type.equals(Type.MONTH)) {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            dateRangMap.put(DateRange.BOTTOM_DATE, cal.getTime());
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            dateRangMap.put(DateRange.TOP_DATE, cal.getTime());
        } else if (this.type.equals(Type.WEEK)) {
            dateRangMap.put(DateRange.TOP_DATE, cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, -7);
            dateRangMap.put(DateRange.BOTTOM_DATE, cal.getTime());
        } else if (this.type.equals(Type.FORNIGHT)) {
            dateRangMap.put(DateRange.TOP_DATE, cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, -14);
            dateRangMap.put(DateRange.BOTTOM_DATE, cal.getTime());
        }
        return dateRangMap;
    }

    public interface OnDateChangeListener {
        void onChange(Map<DateRange, Date> dateRange);
    }

    public void setOnDateChangeListener(OnDateChangeListener listener) {
        this.listener = listener;
    }
}
