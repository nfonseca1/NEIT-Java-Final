package com.example.afinal;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //public static boolean needToRefresh = false;
    ListView listView;
    TextView toolbarNew;
    static ArrayList<Note> notes = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.defaultToolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listView);
        toolbarNew = findViewById(R.id.newBtn);
        toolbarNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.add(new Note());
                int index = notes.size() - 1;

                Intent showNoteActivity = new Intent(getApplicationContext(), NoteActivity.class);
                showNoteActivity.putExtra("INDEX", index);
                startActivity(showNoteActivity);
            }
        });

        if (notes.size() > 0){
            ItemAdapter itemAdapter = new ItemAdapter(this, notes);
            listView.setAdapter(itemAdapter);
            setListListener();
        }
    }

    private void setListListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showNoteActivity = new Intent(getApplicationContext(), NoteActivity.class);
                showNoteActivity.putExtra("INDEX", position);
                startActivity(showNoteActivity);
            }
        });
    }

    public static ArrayList<Note> getNotes(){
        return notes;
    }
}
