package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryList extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        myRecyclerView = (RecyclerView)findViewById(R.id.rv);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        ArrayList<Diary> diaries = new ArrayList<>();
        String[] columns = new String[]{"_id", "name", "description","latitude","longitude","image"};

        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI,
                columns, null, null, null, null);
        if(c != null){

            while(c.moveToNext()){
                int id = c.getInt(0);
                String name = c.getString(1);
                String description = c.getString(2);
                String latitude = c.getString(3);
                String longitude = c.getString(4);
                String image = c.getString(5);

                diaries.add(new Diary(name,description,latitude,longitude,image));
            }
            c.close();
        }
        MyAdapter myAdapter = new MyAdapter(diaries);
        myRecyclerView.setAdapter(myAdapter);


    }
}