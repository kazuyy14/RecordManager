package com.example.kazu.recordmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import org.json.JSONArray;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView myListView;
    ArrayList<String> items = new ArrayList<String>();

    public static ArrayList<String> loadList(Context ctx, String key) {
        ArrayList<String> items = new ArrayList<String>();
        SharedPreferences prefs = ctx.getSharedPreferences("HisList", Context.MODE_PRIVATE);
        String strJson = prefs.getString(key, "");
        if(!strJson.equals("")) {
            try {
                JSONArray jsonAry = new JSONArray(strJson);
                for(int i=0; i<jsonAry.length(); i++) {
                    items.add(jsonAry.getString(i));
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return items;
    }

    public static void saveList(Context ctx, String key, ArrayList<String> items) {
        JSONArray jsonAry = new JSONArray();
        for(int i=0; i<items.size(); i++) {
            jsonAry.put(items.get(i));
        }
        SharedPreferences prefs = ctx.getSharedPreferences("HisList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, jsonAry.toString());
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView =(ListView)findViewById(R.id.myListView);

        items = loadList(getApplicationContext(),"subject");
        

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        myListView.setAdapter(arrayAdapter);


        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = (EditText) findViewById(R.id.addRecord);
                SpannableStringBuilder sp = (SpannableStringBuilder)editText.getText();

                items.add(sp.toString());
                arrayAdapter.notifyDataSetChanged();
                saveList(getApplicationContext(),"subject",items);

                editText.getEditableText().clear();

            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id){
                ListView listView = (ListView)parent;
                String item = (String)listView.getItemAtPosition(pos);

                String point = items.get(pos);

                Intent intent = new Intent(getApplication(),DetailActivity.class);
                startActivity(intent);

            }
        });

        EditText editText = (EditText) findViewById(R.id.addRecord);
        SpannableStringBuilder sp = (SpannableStringBuilder)editText.getText();



    }



}
