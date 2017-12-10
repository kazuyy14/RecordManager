package com.example.kazu.recordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;


public class MainActivity extends AppCompatActivity {

    ListView myListView;
    ArrayList<String> items = new ArrayList<String>();
    Subjects subjects = null;
    RealmList<SubjectDetail> list = null;
    ArrayAdapter<String> arrayAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView =(ListView)findViewById(R.id.myListView);

        final Realm realm = Realm.getDefaultInstance();
        subjects = realm.where(Subjects.class).findFirst();
        if (subjects == null) {
            realm.beginTransaction();
            subjects = realm.createObject(Subjects.class);
            realm.commitTransaction();
        }

        list = subjects.getSubjectDetails();
        for (SubjectDetail s : list) {
            items.add(s.getName());
        }

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        myListView.setAdapter(arrayAdapter);


        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = (EditText) findViewById(R.id.addRecord);
                SpannableStringBuilder sp = (SpannableStringBuilder)editText.getText();

                realm.beginTransaction();
                SubjectDetail detail = new SubjectDetail();
                detail.setName(sp.toString());
                list.add(detail);
                realm.commitTransaction();

                items.add(sp.toString());
                arrayAdapter.notifyDataSetChanged();

                editText.getEditableText().clear();

            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id){
                ListView listView = (ListView)parent;
                String item = (String)listView.getItemAtPosition(pos);

                String point = items.get(pos);

                Intent intent = new Intent(getApplication(),DetailActivity.class);
                intent.putExtra("name", point);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();

        final Realm realm = Realm.getDefaultInstance();
        subjects = realm.where(Subjects.class).findFirst();
        if (subjects == null) {
            realm.beginTransaction();
            subjects = realm.createObject(Subjects.class);
            realm.commitTransaction();
        }

        items.clear();
        list = subjects.getSubjectDetails();
        for (SubjectDetail s : list) {
            items.add(s.getName());
        }
        arrayAdapter.notifyDataSetChanged();

    }



}
