package com.example.thianhe.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtSex, edtAge;
    private Button btnOK;
    private TextView txtR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSex = (EditText) findViewById(R.id.editSex);
        edtAge = (EditText) findViewById(R.id.editAge);
        btnOK = (Button) findViewById(R.id.btnClick);
        txtR = (TextView) findViewById(R.id.resultView);

        btnOK.setOnClickListener(btnOKOnClick);
    }
    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            String strSex= edtSex.getText().toString();
            int iAge = Integer.parseInt(edtAge.getText().toString());

            String strSug=getString(R.string.suggestion);
            if((strSex.equals(getString(R.string.sex_male))) || (strSex.equals(getString(R.string.sex_Male))))
                if(iAge < 30) {
                    strSug += getString(R.string.sug_not_hurry);

                }else if (iAge <= 35) {
                    strSug += getString(R.string.sug_get_married);

                } else {
                    strSug += getString(R.string.sug_find_couple);

                }
            else if((strSex.equals(getString(R.string.sex_female))) || (strSex.equals(getString(R.string.sex_Female))))
                if (iAge < 29) {
                    strSug += getString(R.string.sug_not_hurry);

                } else if (iAge<=32 ) {
                    strSug += getString(R.string.sug_get_married);

                } else {
                    strSug += getString(R.string.sug_find_couple);

                }

            txtR.setText(strSug);
        }
    };
}