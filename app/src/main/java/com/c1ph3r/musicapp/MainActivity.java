package com.c1ph3r.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button pause,forward,rewind,play;
    TextView initTiming,finalTiming;
    SeekBar seeker;
    MediaPlayer myMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pause=(Button) findViewById(R.id.pause);
        forward=(Button) findViewById(R.id.forward);
        rewind=(Button) findViewById(R.id.rewind);
        play=(Button) findViewById(R.id.play);
        seeker=(SeekBar) findViewById(R.id.seekbar);
        initTiming=(TextView) findViewById(R.id.init);
        finalTiming=(TextView) findViewById(R.id.last);
        myMusic=MediaPlayer.create(this,R.raw.Mallipoo);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.start();
            }
        });


    }
}