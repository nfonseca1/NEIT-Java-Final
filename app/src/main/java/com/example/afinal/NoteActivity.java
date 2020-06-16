package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    int index;
    ArrayList<Note> notes;
    TextView title;
    TextView description;
    TextView deleteBtn;
    Button imageBtn;
    ImageView imageView;
    private static final int PICK_IMAGE = 100;
    Uri imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        final int index = intent.getIntExtra("INDEX", -1);
        notes = MainActivity.getNotes();
        this.index = index;

        title = findViewById(R.id.newTitle);
        description = findViewById(R.id.newDescription);
        title.setText(notes.get(index).title);
        description.setText(notes.get(index).description);
        imageURL = notes.get(index).imageURL;

        Toolbar toolbar = findViewById(R.id.noteToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.set(index, new Note(title.getText().toString(), description.getText().toString(), imageURL));

                Intent showNoteActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(showNoteActivity);
            }
        });

        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.remove(index);
                Intent showNoteActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(showNoteActivity);
            }
        });

        imageView = findViewById(R.id.imageView);
        if (notes.get(index).imageURL != null){
            imageView.setImageURI(notes.get(index).imageURL);
        }
        imageBtn = findViewById(R.id.imageBtn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageURL = data.getData();
            imageView.setImageURI(imageURL);
        }
    }
}
