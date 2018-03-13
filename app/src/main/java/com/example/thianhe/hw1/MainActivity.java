package com.example.thianhe.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button btnDoSug;
    private EditText editSex,editAge;
    private TextView editResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDoSug = (Button) findViewById(R.id.btnDoSug);
        editSex = (EditText) findViewById(R.id.editSex);
        editAge = (EditText) findViewById(R.id.editAge);
        editResult = (TextView) findViewById(R.id.editResult);
        btnDoSug.setOnClickListener(btnDoSugOnClick);
    }

    private Button.OnClickListener btnDoSugOnClick = new Button.OnClickListener() {
        public void onClick(View v) {
            String strSex = editSex.getText().toString();
            int iAge = Integer.parseInt(editAge.getText().toString());
            String strSug ="Result: ";
            if(strSex.equals("male") ||strSex.equals("Male"))
                if(iAge<30)
                    strSug += getString(R.string.Not_Hurry);
                else if(iAge<=35)
                    strSug += getString(R.string.Get_Married);
                else if(iAge>35)
                    strSug += getString(R.string.Find_Couple);
            if(strSex.equals("female") ||strSex.equals("Female"))
                if(iAge<28)
                    strSug += getString(R.string.Not_Hurry);
                else if(iAge<=32)
                    strSug +=getString(R.string.Get_Married);
                else if(iAge>32)
                    strSug += getString(R.string.Find_Couple);

            editResult.setText(strSug);
        }
    };
}

