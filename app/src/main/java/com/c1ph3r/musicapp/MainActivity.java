package com.c1ph3r.musicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button pause,forward,rewind,play;
    TextView initTiming,finalTiming;
    ImageView imgview;
    SeekBar seeker;
    MediaPlayer myMusic,music;
    int forwardtime=5000;
    int reversetime=5000;
    double startTime=0;
    double finalTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pause=(Button) findViewById(R.id.pause);
        imgview=(ImageView) findViewById(R.id.img);
        forward=(Button) findViewById(R.id.forward);
        rewind=(Button) findViewById(R.id.rewind);
        play=(Button) findViewById(R.id.play);
        seeker=(SeekBar) findViewById(R.id.seekbar);
        initTiming=(TextView) findViewById(R.id.init);
        finalTiming=(TextView) findViewById(R.id.last);
        myMusic=MediaPlayer.create(this,R.raw.hrudayake_hedarike);
        pause.setEnabled(false);
        seeker.setClickable(false);
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(myMusic.isPlaying()) {
                    seeker.setProgress(myMusic.getCurrentPosition());
                    initTiming.setText(conversion(String.valueOf(myMusic.getCurrentPosition())));
                    imgview.setRotation(imgview.getRotation()+5);
                }
                new Handler().postDelayed(this,100);
            }

        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.start();
                play.setEnabled(false);
                pause.setEnabled(true);
                seeker.setProgress(0);
                seeker.setMax(myMusic.getDuration());
                finalTiming.setText(conversion(String.valueOf(myMusic.getDuration())));
               }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.pause();
                play.setEnabled(true);
                pause.setEnabled(false);
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.seekTo(myMusic.getCurrentPosition()+forwardtime);
            }
        });
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.seekTo(myMusic.getCurrentPosition()-forwardtime);
            }
        });



    }
    static String conversion(String duration){
        long m=Long.parseLong(duration);
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(m)%TimeUnit.HOURS.toMinutes(1),TimeUnit.MILLISECONDS.toSeconds(m)%TimeUnit.MINUTES.toSeconds(1));
    }

}