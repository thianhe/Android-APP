package com.gameusingdynamicfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GameStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_statistics);

        Intent intent = getIntent();
        ((EditText) findViewById(R.id.edtCountSet))
                .setText(String.valueOf(intent.getIntExtra("CountSet", 0)));
        ((EditText) findViewById(R.id.edtCountPlayerWin))
                .setText(String.valueOf(intent.getIntExtra("CountPlayerWin", 0)));
        ((EditText) findViewById(R.id.edtCountComWin))
                .setText(String.valueOf(intent.getIntExtra("CountComWin", 0)));
        ((EditText) findViewById(R.id.edtCountDraw))
                .setText(String.valueOf(intent.getIntExtra("CountDraw", 0)));
        findViewById(R.id.btnBack).setOnClickListener(btnBack_OnClick);
    }

    private View.OnClickListener btnBack_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
