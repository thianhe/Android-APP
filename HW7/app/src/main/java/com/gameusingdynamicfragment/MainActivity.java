package com.gameusingdynamicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.StatisticsInterface {

    public MainFragment.GameResultType mGameResultType;
    public MainFragment mainFragment;
    public Fragment fragResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
    }

    @Override
    public void showGameStatistics(int miCountSet, int miCountPlayerWin, int miCountComWin, int miCountDraw) {
        Intent intent = new Intent(this, GameStatisticsActivity.class);
        intent.putExtra("CountSet", miCountSet);
        intent.putExtra("CountPlayerWin", miCountPlayerWin);
        intent.putExtra("CountComWin", miCountComWin);
        intent.putExtra("CountDraw", miCountDraw);
        startActivity(intent);
    }
}
