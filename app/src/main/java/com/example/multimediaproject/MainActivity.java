package com.example.multimediaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView list;
    TextView label, views, date;
    Switch viewedSong;
    ArrayList<TaylorSwift> taylor;
    private final String STRING_KEY = "the1";
    int star = 0;
    int diffPosition = 0;
    MediaPlayer media;
    int play = 0;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("star", star);
        outState.putParcelableArrayList(STRING_KEY,(ArrayList) taylor);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        label = findViewById(R.id.label);
        viewedSong = findViewById(R.id.viewedSong);
        views = findViewById(R.id.views);
        date = findViewById(R.id.date);

        media = MediaPlayer.create(this, R.raw.the1);

        taylor = new ArrayList<TaylorSwift>();

        taylor.add(new TaylorSwift("Taylor Swift", "Big Machine", "Our Song", 0, 11, R.drawable.taylorswift, "1,232,885,741", "October 24, 2006"));
        taylor.add(new TaylorSwift("Fearless", "Big Machine", "Love Story", 0, 13, R.drawable.fearless, "2,264,422,502", "November 11, 2008"));
        taylor.add(new TaylorSwift("Speak Now", "Big Machine", "Enchanted", 0, 14, R.drawable.speaknow, "2,514,929,349", "October 25, 2010"));
        taylor.add(new TaylorSwift("Red", "Big Machine", "I Knew You Were Trouble", 0, 16, R.drawable.red, "3,123,260,348", "October 22, 2012"));
        taylor.add(new TaylorSwift("1989", "Big Machine", "Blank Space", 0, 13, R.drawable.nine, "7,024,462,187", "October 27, 2014"));
        taylor.add(new TaylorSwift("reputation", "Big Machine", "Look What You Made Me Do", 0, 15, R.drawable.reputation, "7,050,191,673", "November 10, 2017"));
        taylor.add(new TaylorSwift("Lover", "Republic", "Cruel Summer", 0, 18, R.drawable.lover, "8,770,580,024", "August 23, 2019"));
        taylor.add(new TaylorSwift("folklore", "Republic", "cardigan", 0, 16, R.drawable.folklore, "6,537,775,088", "July 24, 2020"));
        taylor.add(new TaylorSwift("evermore", "Republic", "willow", 0, 15, R.drawable.evermore, "3,754,706,724", "December 11, 2020"));
        taylor.add(new TaylorSwift("Midnights", "Republic", "Anti-Hero", 0, 13, R.drawable.midnights, "6,280,859,123", "October 21, 2022"));

        list.setFocusable(false);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_layout, taylor);
        list.setAdapter(adapter);

        if (play == 0) {
            media.start();
            play++;
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Turn to landscape mode to learn more", Toast.LENGTH_SHORT).show();
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (savedInstanceState != null) {
                taylor = savedInstanceState.<TaylorSwift>getParcelableArrayList("the1");
                star = savedInstanceState.getInt("star");

                media.stop();

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        label.setText(taylor.get(position).getLabel());
                        views.setText(taylor.get(position).getViews());
                        date.setText(taylor.get(position).getReleaseDate());
                        // checking

                        if (diffPosition != list.getCheckedItemPosition()) {
                            viewedSong.setChecked(false);
                        }

                        viewedSong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    Toast.makeText(MainActivity.this, taylor.get(position).getMostViewedSong(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                });

            }
            list.setAdapter(new CustomAdapter(this, R.layout.adapter_layout, taylor));
        }

        else { // portrait
            if (savedInstanceState != null) {
                taylor = savedInstanceState.<TaylorSwift>getParcelableArrayList("the1");
                star = savedInstanceState.getInt("star");

                media.stop();
            }
            list.setAdapter(new CustomAdapter(this, R.layout.adapter_layout, taylor));
        }

    }//closes onCreate

}