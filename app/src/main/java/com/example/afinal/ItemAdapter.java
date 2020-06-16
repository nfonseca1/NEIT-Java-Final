package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Note> notes;

    public ItemAdapter(Context c, ArrayList<Note> n){
        notes = n;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.my_listview_detail, null);
        final TextView titleView = v.findViewById(R.id.Title);
        final TextView descriptionView = v.findViewById(R.id.Description);
        ImageView imageView = v.findViewById(R.id.preview);

        String title = notes.get(position).title;
        String description = notes.get(position).description;

        titleView.setText(title);
        descriptionView.setMaxLines(1);
        descriptionView.setText(description);
        if (notes.get(position).imageURL != null){
            imageView.setImageURI(notes.get(position).imageURL);
        }
        return v;
    }
}
