package com.example.arsuser.multimedia;

import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.time.chrono.ThaiBuddhistEra;
import java.util.Random;

public class GraphicsActivity extends AppCompatActivity {

    Bitmap bmp;     //Androidプログラム上で「画像」を表現するクラス
    SurfaceView surface;        //画面部品の一種。　ほかの部品と比べて機能を絞ることで軽量化されている。
    SurfaceHolder holder;
    Thread thread;      //並行処理の仕組みを提供するクラス

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        bmp = BitmapFactory.decodeResource(this.getResources(),R.drawable.pumpkin);

        surface = findViewById(R.id.surfaceView);
        holder = surface.getHolder();
        SurfaceCallback callback = new SurfaceCallback();//
        holder.addCallback(callback);
        holder.setFixedSize(surface.getWidth(),surface.getHeight());

        //追加
        surface.setOnTouchListener(callback);
    }

    private class SurfaceCallback implements SurfaceHolder.Callback,Runnable,View.OnTouchListener{

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread = new Thread(this);
            thread.start();
            //thread.run()では開始しないので注意
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            //Surfaceviewが破棄(例：アプリ終了)されたとき。
            thread = null;
        }

        @Override
        public void run() {
            Canvas c;
            int x = 0,y = 0,hx = 0,hy = 1,rx = 3,ry = 3;
            Random r = new Random();
            rx = r.nextInt(5)+1;
            ry = r.nextInt(5)+1;

            while(thread != null){
                c = holder.lockCanvas();
                c.drawColor(Color.WHITE);
                c.drawBitmap(bmp,x + hx, y + hy, null);
                holder.unlockCanvasAndPost(c);

                if ( x + bmp.getWidth() > surface.getWidth() || x < 0 ){
                    if (rx<0) {
                        x=0;
                        rx = r.nextInt(7) + 1;
                    }else{
                        x=surface.getWidth()-bmp.getWidth();
                        rx = r.nextInt((7)+1) * -1;
                    }
                }
                if ( y + bmp.getHeight() > surface.getHeight() || y < 0 ){
                    if (ry<0) {
                        y=0;
                        ry = r.nextInt(7) + 1;
                    }else{
                        y = surface.getHeight()-bmp.getHeight();
                        ry = r.nextInt((7)+1) * -1;
                    }
                }
                x = x + rx;
                y = y + ry;
            }

        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
           int x = (int)event.getX();
           int y = (int)event.getY();

            return false;

        }

    }
}
