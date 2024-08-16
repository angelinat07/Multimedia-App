package com.example.multimediaproject;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<TaylorSwift>{

    List<TaylorSwift> list;
    Context context;
    int xmlResource;


    public CustomAdapter(Context context, int resource, List<TaylorSwift> objects) {
        super(context, resource, objects);
        list = objects;
        this.context = context;
        xmlResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            return super.getView(position, convertView, parent); //muting this because we are customizing the layout
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterLayout = layoutInflater.inflate(xmlResource, null);

// change stuff underneath for project
        TextView name = adapterLayout.findViewById(R.id.name);
        TextView songNum = adapterLayout.findViewById(R.id.songNum);
        RatingBar ratingBar = adapterLayout.findViewById(R.id.ratingBar);
        Button remove = adapterLayout.findViewById(R.id.remove);
        ImageView image = adapterLayout.findViewById(R.id.image);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        ratingBar.setProgress(getItem(position).getRating());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                getItem(position).setRating((int)(rating));
                notifyDataSetChanged();
            }
        });

        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            image.setImageResource(getItem(position).getImage());
            name.setText(getItem(position).getAlbum());
            songNum.setText("# Songs: " + (getItem(position)).getNumOfSongs());
        }

        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            image.setImageResource(getItem(position).getImage());
            name.setText(getItem(position).getAlbum());
            songNum.setText("# Songs: " + (getItem(position)).getNumOfSongs());
        }
        return adapterLayout;

    }

}