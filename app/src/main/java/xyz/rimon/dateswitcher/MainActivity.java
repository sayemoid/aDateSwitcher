package xyz.rimon.dateswitcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;
import java.util.Map;

import xyz.rimon.adateswitcher.DateSwitcher;

public class MainActivity extends AppCompatActivity {

    private DateSwitcher mDateSwitcher;
    private DateSwitcher wDateSwitcher;
    private DateSwitcher fDateSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mDateSwitcher = findViewById(R.id.mDateSwitcher);
        this.wDateSwitcher = findViewById(R.id.wDateSwitcher);
        this.fDateSwitcher = findViewById(R.id.fDateSwitcher);

        this.wDateSwitcher.setType(DateSwitcher.Type.WEEK);
        this.fDateSwitcher.setType(DateSwitcher.Type.FORNIGHT);
//        this.dateSwitcher.setType(DateSwitcher.Type.MONTH);

        this.mDateSwitcher.setOnDateChangeListener(new DateSwitcher.OnDateChangeListener() {
            @Override
            public void onChange(Map<DateSwitcher.DateRange, Date> dateRange) {

                Date topDate = dateRange.get(DateSwitcher.DateRange.TOP_DATE);
                Date bottomDate = dateRange.get(DateSwitcher.DateRange.BOTTOM_DATE);

                Log.i("DATE_RANGE","TOP: " + topDate.toString()
                        + "\nBOTTOM: " + bottomDate.toString());
            }
        });
    }
}
