package edu.ntut.user.myhw3;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainActivity self;
    private RadioGroup mRadSex;
    private Spinner mSpnAge;
    private RadioButton mRadBtnMale;
    private RadioButton mRadBtnFemale;
    private ArrayList<CheckBox> listChkHobby;
    private Button mBtnOK;
    private TextView mTxtSug;
    private TextView mTxtHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self = this;
        mRadSex = (RadioGroup) findViewById(R.id.radGrpSex);
        mSpnAge = (Spinner) findViewById(R.id.spAge);
        mRadBtnMale = (RadioButton) findViewById(R.id.radBtnSexMale);
        mRadBtnFemale = (RadioButton) findViewById(R.id.radBtnSexFemale);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);

        listChkHobby = new ArrayList<>();
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyMusic));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbySing));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyClimbling));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyEating));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyDance));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyTravel));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyReading));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyWriting));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbySwim));
        listChkHobby.add((CheckBox) findViewById(R.id.chkHobbyDrawing));

        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTxtHobby = (TextView) findViewById(R.id.txtHobby1);
        mRadSex.setOnCheckedChangeListener(spAge_OnCheckedChange);              // 當「性別」更動時
        mBtnOK.setOnClickListener(btnOKOnClick);
    }

    private RadioGroup.OnCheckedChangeListener spAge_OnCheckedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            RadioButton target = (RadioButton) radioGroup.findViewById(id);
            Resources res = getResources();
            ArrayAdapter<String> adapter;

            if (target == mRadBtnMale) {
                adapter = new ArrayAdapter<String>(self, android.R.layout.simple_list_item_1, res.getStringArray(R.array.maleAge_list));
            }
            else {
                adapter = new ArrayAdapter<String>(self, android.R.layout.simple_list_item_1, res.getStringArray(R.array.femaleAge_list));
            }

            mSpnAge.setAdapter(adapter);
        }
    };
    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MarriageSuggestion ms = new MarriageSuggestion();
            boolean isMale = mRadBtnMale.isChecked();
            String sexStr = isMale ? "male" : "female";

            if (isMale) {
                switch(mSpnAge.getSelectedItem().toString()) {
                    case "小於30歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 1));
                        break;
                    case "30~40歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 2));
                        break;
                    case "大於40歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 3));
                }
            }
            else {
                switch(mSpnAge.getSelectedItem().toString()) {
                    case "小於28歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 1));
                        break;
                    case "28~35歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 2));
                        break;
                    case "大於35歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 3));
                }
            }

            String hobbyInfo = "興趣: ";
            for (CheckBox radio : listChkHobby) {
                if (radio.isChecked()) {
                    hobbyInfo += radio.getText().toString() + " ";
                }
            }

            mTxtHobby.setText(hobbyInfo);
        }
    };


}
