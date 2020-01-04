package com.example.anmol.courtcounter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class menuAdapter extends ArrayAdapter<menuItem> {

    public menuAdapter(Activity context, ArrayList<menuItem> item)
    {
        super(context,0, item);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }
        menuItem currentItem = getItem(position);

        TextView title_TextView  = (TextView) listItemView.findViewById(R.id.title);
        title_TextView.setText(currentItem.getTitle());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentItem.getImageResourceID());

        return listItemView;
    }
}
