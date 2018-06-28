package com.example.abhinav.file_explorer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class pic extends AppCompatActivity {
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        Bundle os=getIntent().getExtras();
        f= (File) os.get("value");
        try {
            display(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void display(File f) throws FileNotFoundException {
        FileInputStream fis=null;
        fis=new FileInputStream(f);
        Bitmap b= BitmapFactory.decodeStream(fis);
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView iv=(ImageView)findViewById(R.id.iv);
        iv.setImageBitmap(b);
    }

}
