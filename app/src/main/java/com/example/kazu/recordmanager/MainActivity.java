package com.example.kazu.recordmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView myListView;
    ArrayList<String> items = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView =(ListView)findViewById(R.id.myListView);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        myListView.setAdapter(arrayAdapter);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                items.add("value");
                arrayAdapter.notifyDataSetChanged();

            }
        });

        findViewById(R.id.delButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                items.clear();
                arrayAdapter.notifyDataSetChanged();
            }
        });


    }



}
