package com.example.arsuser.multimedia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import static java.lang.Thread.sleep;

public class GameActivity extends AppCompatActivity implements Runnable {

    SurfaceHolder holder;
    Thread thread;      //並行処理の仕組みを提供するクラス



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygame);

        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surfaceView_ゲーム画面);
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
                return false;
            }
        });

        final MainActivity.GameHero hero = new MainActivity.GameHero();


        Button start = findViewById(R.id.button_スタートボタン);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run();

            }
        });

    }

    @Override
    public void run() {

    }
}

