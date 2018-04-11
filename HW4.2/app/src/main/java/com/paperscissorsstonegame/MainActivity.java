package com.paperscissorsstonegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtComPlay, mTxtResult;
    private Button mBtnScissors, mBtnStone, mBtnPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtComPlay = (TextView)findViewById(R.id.txtComPlay);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mBtnScissors = (Button)findViewById(R.id.btnScissors);
        mBtnStone = (Button)findViewById(R.id.btnStone);
        mBtnPaper = (Button)findViewById(R.id.btnPaper);

        mBtnScissors.setOnClickListener(btnScissorsOnClick);
        mBtnStone.setOnClickListener(btnStoneOnClick);
        mBtnPaper.setOnClickListener(btnPaperOnClick);
    }

    NewMethod cp = new NewMethod();
    private View.OnClickListener btnScissorsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 1 – 剪刀, 2 – 石頭, 3 – 布.
            int iComPlay = (int)(Math.random()*3 + 1);
            mTxtComPlay.setText(cp.getComputerPlay(iComPlay));
            mTxtResult.setText(cp.getResult(1,iComPlay));
        }
    };

    private View.OnClickListener btnStoneOnClick = new View.OnClickListener() {
        public void onClick(View v) {

            int iComPlay = (int)(Math.random()*3 + 1);
            mTxtComPlay.setText(cp.getComputerPlay(iComPlay));
            mTxtResult.setText(cp.getResult(2,iComPlay));
        }
    };

    private View.OnClickListener btnPaperOnClick = new View.OnClickListener() {
        public void onClick(View v) {

            int iComPlay = (int)(Math.random()*3 + 1);
            mTxtComPlay.setText(cp.getComputerPlay(iComPlay));
            mTxtResult.setText(cp.getResult(3,iComPlay));
        }
    };
}
