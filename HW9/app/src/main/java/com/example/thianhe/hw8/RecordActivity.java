package com.example.thianhe.hw8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent = getIntent();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter.addAll(intent.getStringArrayListExtra("dataList"));
        ((ListView) findViewById(R.id.itemList)).setAdapter(adapter);
    }
}
