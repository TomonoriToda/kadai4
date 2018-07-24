package com.example.arsuser.multimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.IOException;

public class MediaControlActivity extends AppCompatActivity {
    private MediaPlayer _player;
    private Button _btPlay;
    private Button _btBack;
    private Button _btForward;


    @Override
    protected void onDestroy() {
        //親クラスのメソッド呼び出し
        super.onDestroy();
        //プレーヤーが再生中なら…
        if(_player.isPlaying()){
            //プレーヤーを停止
            _player.stop();
        }
        //プレーヤーを開放
        _player.release();
        //プレーヤー用フィールドをnullに
        _player = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madia_control);

        _btPlay=findViewById(R.id.button8);
        _btBack=findViewById(R.id.button7);
        _btForward=findViewById(R.id.button9);

        _player = new MediaPlayer();
        String mediaFileUriStr = "android/resource://" + getPackageName() + "/" + R.raw.splashes;
        Uri mediaFileUri = Uri.parse(mediaFileUriStr);
        try {
            _player.setDataSource(MediaControlActivity.this,mediaFileUri);
            _player.setOnPreparedListener(new PlayerPreparedListener());
            _player.setOnCompletionListener(new PlayerCompletionListener());
            _player.prepareAsync();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //スイッチを取得
        Switch loopSwitch = findViewById(R.id.switch2);
        //スイッチにリスナを設定
        loopSwitch.setOnCheckedChangeListener(new LoopSwitchChangedListener());
    }

    private class PlayerPreparedListener implements MediaPlayer.OnPreparedListener{
        @Override
        public void onPrepared(MediaPlayer mp) {
            _btPlay.setEnabled(true);
            _btBack.setEnabled(true);
            _btForward.setEnabled(true);
        }
    }

    private class PlayerCompletionListener implements  MediaPlayer.OnCompletionListener{
        @Override
        public void onCompletion(MediaPlayer mp) {
            _btPlay.setText(R.string.bt_play_play);
        }
    }

    private class LoopSwitchChangedListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //ループするかどうかを設定
            _player.setLooping(isChecked);
        }
    }


    //再生ボタンが押された時の処理
    //条件がそろっていれば、レイアウトと↓の紐づけが可能
    public void onPlayButtonClick(View view){
        if(_player.isPlaying()){
            _player.pause();
            _btPlay.setText(R.string.bt_play_play);
        }
        else {
            _player.start();
            _btPlay.setText(R.string.bt_play_pause);
        }
    }

    public void onBackButtonClick(View view){
        //再生位置を先頭に変更
        _player.seekTo(0);
    }

    public void onForwardButonClick(View view){
        //現在再生中のメディアファイルの長さを取得
        int duration = _player.getDuration();
        //再生位置を終端に変更
        _player.seekTo(duration);
        //再生中でないなら
        if (!_player.isPlaying()){
            //再生を開始
            _player.start();
        }
    }
}
