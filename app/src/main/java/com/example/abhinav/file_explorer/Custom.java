package com.example.abhinav.file_explorer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Custom extends ArrayAdapter<Folder>{
    TextView tv1,tv2,tv3;
    ImageView iv;
    View v;

    Custom(Context context, ArrayList<Folder> al){
        super(context,R.layout.custom,al);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li=(LayoutInflater.from(getContext()));
         v=li.inflate(R.layout.custom,parent,false);
        tv1=(TextView)v.findViewById(R.id.tv1);
        tv2=(TextView)v.findViewById(R.id.tv2);
        tv3=(TextView)v.findViewById(R.id.tv3);
        iv=(ImageView)v.findViewById(R.id.iv);
        Folder item=(Folder)getItem(position);
        tv1.setText(item.getName());
        tv2.setText(item.getSize());
        tv3.setText(item.getDate());
        String image=item.getImage();
         if(image=="photo"){
            File f=item.getPath();
            display(f);
        }
        else if(image=="file_icon")
            iv.setImageResource(R.drawable.file_icon);
        else if(image=="directory_icon")
            iv.setImageResource(R.drawable.directory_icon);

        return v;
    }

    public void display(File f){
        FileInputStream fis=null;
        try {
            fis=new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr=new InputStreamReader(fis);
        Bitmap b= BitmapFactory.decodeStream(fis);
        iv.setImageBitmap(b);

    }
}
