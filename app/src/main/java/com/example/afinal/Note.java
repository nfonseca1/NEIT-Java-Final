package com.example.afinal;

import android.net.Uri;

public class Note {

    String title = "";
    String description = "";
    Uri imageURL = null;

    public Note(){}
    public Note(String title, String description, Uri uri){
        this.title = title;
        this.description = description;
        this.imageURL = uri;
    }
}
