package com.c1ph3r.musicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    //Declaring the variables for Main activity
    Button pause,forward,rewind,play;
    TextView initTiming,finalTiming;
    ImageView imgview;
    SeekBar seeker;
    MediaPlayer myMusic,music;
    int forwardtime=5000;
    int reversetime=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //defining the usage for the variables created globally.
        //In this method we are going to comprise the actions to be done for the music application.
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); // Making orientation without sensor so it will help in avoiding restart of the layout.
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
        myMusic=MediaPlayer.create(this,R.raw.manavalan_thug); // setting a song for this(present) activity in media player
        pause.setEnabled(false);
        seeker.setClickable(false);
        //used for setting seekbar's progress and song's current timing detector
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(myMusic.isPlaying()) {
                    seeker.setProgress(myMusic.getCurrentPosition());
                    initTiming.setText(conversion(String.valueOf(myMusic.getCurrentPosition())));
                    imgview.setRotation(imgview.getRotation()+5);
                }
                new Handler().postDelayed(this,300); //using handler method for delaying the runnable method for 300-milliSeconds
            }
        });
        //Button play's actions
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.start();
                play.setEnabled(false);
                pause.setEnabled(true);
                Toast.makeText(MainActivity.this,"Playing",Toast.LENGTH_SHORT).show();
                seeker.setProgress(0);
                seeker.setMax(myMusic.getDuration());
                finalTiming.setText(conversion(String.valueOf(myMusic.getDuration())));// usage of method conversion
               }
        });
        //Button pause's actions
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.pause();
                play.setEnabled(true);
                pause.setEnabled(false);
                Toast.makeText(MainActivity.this,"Paused",Toast.LENGTH_SHORT).show();
            }
        });
        //Button forward's actions
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.seekTo(myMusic.getCurrentPosition()+forwardtime);
            }
        });
        //Button rewind's actions
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMusic.seekTo(myMusic.getCurrentPosition()-reversetime);
            }
        });
    }
    //method created for converting the format of the duration captured...
    // ...from media player into human readable(milliseconds into Minutes and Seconds)
    static String conversion(String duration){
        long m=Long.parseLong(duration);
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(m)%TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(m)%TimeUnit.MINUTES.toSeconds(1));
    }

}