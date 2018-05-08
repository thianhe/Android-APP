package com.gameusingdynamicfragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainFragment extends Fragment {

    public enum GameResultType {
        TYPE_1
    }
    private final int[] diceImages = new int[] { R.drawable.dice01, R.drawable.dice02,
                                                 R.drawable.dice03, R.drawable.dice04,
                                                 R.drawable.dice05, R.drawable.dice06  };
    // 所屬的 Activity 必須實作以下介面中的callback方法
    public interface StatisticsInterface {
        void showGameStatistics(int miCountSet, int miCountPlayerWin, int miCountComWin, int miCountDraw);
    };


    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0, miCountPlayerWin = 0, miCountComWin = 0, miCountDraw = 0;
    private ImageButton mIbtnDice;
    private Button mBtnShowResult,mbtnThrowDice;

    private boolean isDiceRolling = false;
    StatisticsInterface statisticsInterface;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            statisticsInterface = (StatisticsInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement GameFragment.StatisticsInterface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 利用inflater物件的inflate()方法取得介面佈局檔，並將最後的結果傳回給系統
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mIbtnDice = (ImageButton) getView().findViewById(R.id.imgRollingDice);

        mbtnThrowDice = (Button) getView().findViewById(R.id.btnThrowDice);
        mbtnThrowDice.setOnClickListener(btnDiceOnClick);

        mBtnShowResult = (Button) getView().findViewById(R.id.btnShowResult);
        mBtnShowResult.setOnClickListener(btnShowResultOnClick);

    }

    public void ResultOneRoundDice() {
        int number = (int) Math.floor(Math.random() * 6);
        miCountSet += 1;
        mIbtnDice.setImageDrawable(getResources().getDrawable(diceImages[number]));
        switch (number / 2) {
            case 0:
                Toast.makeText(getContext(), getString(R.string.player_win), Toast.LENGTH_SHORT).show();
                miCountPlayerWin += 1;
                break;
            case 1:
                Toast.makeText(getContext(), getString(R.string.player_draw), Toast.LENGTH_SHORT).show();
                miCountDraw += 1;
                break;
            case 2:
                Toast.makeText(getContext(), getString(R.string.player_lose), Toast.LENGTH_SHORT).show();
                miCountComWin += 1;
                break;
        }

    }

    private final View.OnClickListener btnDiceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (MainFragment.this.isDiceRolling) return;
            MainFragment.this.isDiceRolling = true;

            Resources res = getResources();
            final AnimationDrawable animDraw = (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            mIbtnDice.setImageDrawable(animDraw);
            animDraw.start();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    animDraw.stop();
                    isDiceRolling = false;
                    ResultOneRoundDice();
                }
            }, 4000);

        }
    };

    private View.OnClickListener btnShowResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            statisticsInterface.showGameStatistics(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
        }
    };
}
