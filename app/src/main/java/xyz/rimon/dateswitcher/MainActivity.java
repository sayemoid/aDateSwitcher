package xyz.rimon.dateswitcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.Map;

import xyz.rimon.adateswitcher.DateSwitcher;

public class MainActivity extends AppCompatActivity {

    private DateSwitcher dateSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dateSwitcher = findViewById(R.id.dateSwitcher);
        this.dateSwitcher.setType(DateSwitcher.Type.WEEK);

        this.dateSwitcher.setOnDateChangeListener(new DateSwitcher.OnDateChangeListener() {
            @Override
            public void onChange(Map<DateSwitcher.DateRange, Date> dateRange) {
                Toast.makeText(getApplicationContext(),
                        "TOP: " + dateRange.get(DateSwitcher.DateRange.TOP_DATE).toString()
                                + "\nBOTTOM: " + dateRange.get(DateSwitcher.DateRange.BOTTOM_DATE).toString(),
                        Toast.LENGTH_SHORT).show();
                Log.i("DATE_RANGE","TOP: " + dateRange.get(DateSwitcher.DateRange.TOP_DATE).toString()
                        + "\nBOTTOM: " + dateRange.get(DateSwitcher.DateRange.BOTTOM_DATE).toString());
            }
        });
    }
}
