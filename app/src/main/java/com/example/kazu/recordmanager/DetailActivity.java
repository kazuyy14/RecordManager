package com.example.kazu.recordmanager;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmList;

public class DetailActivity extends AppCompatActivity {

    Realm realm = null;
    SubjectDetail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String name = getIntent().getStringExtra("name");
        realm = Realm.getDefaultInstance();
        Subjects s = realm.where(Subjects.class).findFirst();
        RealmList<SubjectDetail> list = s.getSubjectDetails();

        for (SubjectDetail subjectDetail : list){
            if(name.equals(subjectDetail.getName())){
                detail = subjectDetail;
            }
        }

        EditText editText = (EditText) findViewById(R.id.recordName) ;
        editText.setText(detail.getName());

        EditText editText1 = (EditText) findViewById(R.id.unitScore) ;
        editText1.setText(String .valueOf(detail.getUnit()));

        Spinner spinner = (Spinner) findViewById(R.id.spinner) ;
        spinner.setSelection(detail.getStatus());

        final TextView textView = (TextView) findViewById(R.id.attendanceScore) ;
        textView.setText(String.valueOf(detail.getAttendanceCount()));

        EditText editText2 = (EditText) findViewById(R.id.freeSpace) ;
        editText2.setText(detail.getMemo());


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                realm.beginTransaction();
                detail.setName(s.toString());
                realm.commitTransaction();

            }
        });

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = 0;
                try{
                    i = Integer.parseInt(s.toString());
                }catch (NumberFormatException e){
                }

                realm.beginTransaction();
                detail.setUnit(i);
                realm.commitTransaction();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                realm.beginTransaction();
                detail.setStatus(position);
                realm.commitTransaction();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                realm.beginTransaction();
                detail.setMemo(s.toString());
                realm.commitTransaction();

            }
        });





        View backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        View plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                detail.setAttendanceCount(detail.getAttendanceCount()+1);
                realm.commitTransaction();
                textView.setText(Integer.toString(detail.getAttendanceCount()));

            }
        });

        View minus = findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                detail.setAttendanceCount(detail.getAttendanceCount()-1);
                realm.commitTransaction();
                textView.setText(Integer.toString(detail.getAttendanceCount()));
            }
        });

    }



}
