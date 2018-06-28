package com.example.abhinav.file_explorer;

import java.io.File;

public class Folder {
    String name,date,size;
    File path,parent;
    String image;
    Folder(String name,String date,String size,File path,File parent,String image){
        this.name=name;
        this.date=date;
        this.size=size;
        this.path=path;
        this.parent=parent;
        this.image=image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getSize() {
        return size;
    }

    public File getPath() {
        return path;
    }

    public File getParent() {
        return parent;
    }

    public String getImage() {
        return image;
    }
}

