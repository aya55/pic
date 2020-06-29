package com.example.diva;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button,button1;
    private static final int PICK_IMAGE = 1;
    Bitmap bitmap , bitmap1;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button =  findViewById(R.id.bttn);
        button1 = findViewById(R.id.bttn1);
         imageView =  findViewById(R.id.img);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select a picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
               bitmap1=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
               imageView.setImageBitmap(bitmap1);

            }catch (IOException e){
             e.printStackTrace();


            }

        }

    }
}
