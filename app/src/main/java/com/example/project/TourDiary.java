package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class TourDiary extends AppCompatActivity {

    TextView name,description;
    double lat,lon;
    ImageView img;
    String imgUri;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_diary);

        name = (TextView)findViewById(R.id.textView3);
        description = (TextView)findViewById(R.id.textView5);
        img = (ImageView)findViewById(R.id.imageView);
        location = (TextView)findViewById(R.id.textView_map);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        lat = Double.parseDouble(intent.getStringExtra("latitude"));
        lon = Double.parseDouble(intent.getStringExtra("longitude"));
        imgUri = intent.getStringExtra("image");
        showImage(Uri.parse(imgUri));

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourDiary.this, MyMap.class);
                intent.putExtra("map_latitude",lat);
                intent.putExtra("map_longitude",lon);
                startActivity(intent);
            }
        });
    }

    private void showImage(Uri uri) {
        try{
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            img.setImageBitmap(bitmap);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}