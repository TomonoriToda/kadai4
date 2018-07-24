package com.example.arsuser.multimedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(MainActivity.this, MediaControlActivity.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(MainActivity.this, GraphicsActivity.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });

        Button bt_game = (Button)findViewById(R.id.button3);
        bt_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });




    }


    //クラスの作成
    public static class GameHero{
        Bitmap bmp;
        float x;
        float y;
        int atk;
        float r;

        public GameHero() {
            this.bmp = bmp;
            this.x = 100;
            this.y = 150;
            this.atk = 10;
            this.r = 10;
        }
    }

    public class GameEnemy{
        Bitmap bmp1;
        Bitmap bmp2;
        Bitmap bmp3;
        float x;
        float y;
        int hp;
        float r;
        boolean dead;

        public GameEnemy() {
            this.bmp1 = bmp1;
            this.bmp2 = bmp2;
            this.bmp3 = bmp3;
            this.x = x;
            this.y = y;
            this.hp = hp;
            this.r = r;
            this.dead = dead;
        }
    }

}
