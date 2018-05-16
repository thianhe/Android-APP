package com.example.thianhe.hw8;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner spinItem;
    private EditText edtDate;
    private EditText edtCost;

    private StringBuilder stringBuilder;
    private Formatter formatter;
    private ArrayList<String> dataList;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinItem = findViewById(R.id.spnOption);
        edtDate = findViewById(R.id.editDate);
        edtDate.setEnabled(false);
        edtCost = findViewById(R.id.editCost);

        ((DatePicker) findViewById(R.id.dpkDataPicker)).setOnDateChangedListener(dpkDatePicker_OnDateChanged);
        ((CalendarView) findViewById(R.id.calendarView)).setOnDateChangeListener(cldCalendar_OnDateChanged);
        findViewById(R.id.btnInput).setOnClickListener(btnInput_OnClick);
        findViewById(R.id.btnRecord).setOnClickListener(btnRecord_OnClick);

        stringBuilder = new StringBuilder();
        formatter = new Formatter(stringBuilder, Locale.TAIWAN);
        dataList = new ArrayList<>();
        number = 0;
    }

    private final DatePicker.OnDateChangedListener dpkDatePicker_OnDateChanged = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
            edtDate.setText(date);
        }
    };

    private final CalendarView.OnDateChangeListener cldCalendar_OnDateChanged = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            String date = year + "/" + (month + 1) + "/" + dayOfMonth;
            edtDate.setText(date);
        }
    };

    private final View.OnClickListener btnInput_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String itemName = spinItem.getSelectedItem().toString();
            String date = edtDate.getText().toString();
            String cost = edtCost.getText().toString();
            stringBuilder.delete(0, stringBuilder.length());
            formatter.format("項目%d  %10s  %10s  %5s", number++, date, itemName, cost);
            dataList.add(stringBuilder.toString());
            Toast.makeText(MainActivity.this, cost, Toast.LENGTH_SHORT).show();
        }
    };

    private final View.OnClickListener btnRecord_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            intent.putStringArrayListExtra("dataList", dataList);
            startActivity(intent);
        }
    };
}
