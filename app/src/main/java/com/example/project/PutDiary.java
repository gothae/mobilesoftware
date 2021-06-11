package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PutDiary extends AppCompatActivity {

    Button b1,b2;
    EditText name,description,latitude,longitude;
    ImageView img;
    private final int GET_GALLERY_IMAGE = 200;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            img.setImageURI(imageUri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_diary);

        b1 = (Button)findViewById(R.id.btn_save);
        b2 = (Button)findViewById(R.id.btn_upload);
        name = (EditText) findViewById(R.id.editText_Name);
        description = (EditText)findViewById(R.id.editText_description);
        latitude = (EditText)findViewById(R.id.editText_latitude);
        longitude = (EditText)findViewById(R.id.editText_longitude);
        img = (ImageView)findViewById(R.id.imageView2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues addValues = new ContentValues();

                addValues.put(MyContentProvider.NAME, name.getText().toString());
                addValues.put(MyContentProvider.DESCRIPTION, description.getText().toString());
                addValues.put(MyContentProvider.LATITUDE, latitude.getText().toString());
                addValues.put(MyContentProvider.LONGITUDE, longitude.getText().toString());
                addValues.put(MyContentProvider.IMAGE,img.toString());

                getContentResolver().insert(MyContentProvider.CONTENT_URI,addValues);
                Toast.makeText(getBaseContext(),"SAVE COMPLETE",
                        Toast.LENGTH_LONG).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,GET_GALLERY_IMAGE);
            }
        });

    }
}