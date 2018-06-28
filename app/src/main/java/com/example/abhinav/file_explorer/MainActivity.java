package com.example.abhinav.file_explorer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Folder> alf;
    ListView lv;
    ArrayList<File> alfi=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9051);
            else
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9051);
        }
        getFiles(Environment.getExternalStorageDirectory());
    }

    public void getFiles(File file) {
        alf=new ArrayList<>();
        File f[] = file.listFiles();
        for (File fi : f) {

                String name=fi.getName();
                Date d=new Date(fi.lastModified());
                DateFormat df=DateFormat.getDateTimeInstance();
                String date=df.format(d);
                File path=new File(fi.getAbsolutePath());
                File parent=new File(fi.getParent());
                String size="";
                String image="";
            if (fi.isDirectory()){
                 size=Integer.toString(fi.listFiles().length)+"items";
                  image="directory_icon";
            }
            else{
                if(fi.getName().endsWith(".jpg")||fi.getName().endsWith(".png"))
                    image="photo";
                else
                    image="file_icon";

                size=Integer.toString((int) fi.length())+"bytes";
            }


            alf.add(new Folder(name,date,size,path,parent,image));


        }
        view();
    }

    public void view(){
        ArrayAdapter<Folder> aa=new Custom(this,alf);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Folder f=(Folder) adapterView.getItemAtPosition(i);
                if(f.getName().endsWith(".txt")){
                    open obj=new open(f);
                    alfi.add(f.getParent());
                }
                else if(f.getName().endsWith(".jpg")||f.getName().endsWith(".png")) {
                    f.setImage("photo");
                    Intent in=new Intent(MainActivity.this,pic.class);
                    in.putExtra("value",f.getPath());
                    startActivity(in);
                    alfi.add(f.getParent());
                }
                else
                    getFiles(f.getPath());
                alfi.add(f.getParent());

            }
        });
    }

    @Override
    public void onBackPressed() {
        int length=alfi.size();
        if(length==0)
            onStop();
        getFiles(alfi.get(length-1));
        alfi.remove(length-1);
    }
}

