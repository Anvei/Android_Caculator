package com.example.caculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    private List<MyObject> mList = initList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        ((TextView)findViewById(R.id.title_title)).setText("Activity2");

        MyAdapter adapter = new MyAdapter(this, R.layout.sub_view, mList);
        ListView mListView = (ListView)findViewById(R.id.listView);
        mListView.setAdapter(adapter);
    }

    protected static List<MyObject> initList(){
        List<MyObject> list = new ArrayList<>();
        for(int i = 1; i <= 9600; i++){
            MyObject object = new MyObject(String.valueOf(i));
            list.add(object);
        }
        return list;
    }
}