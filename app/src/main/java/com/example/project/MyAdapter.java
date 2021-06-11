package com.example.project;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_description;
        TextView latitude;
        TextView longitude;

        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_item);
        }
    }
    private ArrayList<Diary> myDiaryList;
    MyAdapter(ArrayList<Diary> diarys){
        this.myDiaryList = diarys;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,
                parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv_name.setText(myDiaryList.get(position).getName());
//        myViewHolder.tv_description.setText(myDiaryList.get(position).getDescription());

        myViewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TourDiary.class);
                intent.putExtra("name",myDiaryList.get(position).getName());
                intent.putExtra("description",myDiaryList.get(position).getDescription());
                intent.putExtra("latitude",myDiaryList.get(position).getLatitude());
                intent.putExtra("longitude",myDiaryList.get(position).getLongitude());
                intent.putExtra("image",myDiaryList.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDiaryList.size();
    }
}
