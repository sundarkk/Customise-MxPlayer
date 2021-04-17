package com.example.mxvideoplayer;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.khizar1556.mkvideoplayer.MKPlayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;
public class videoplayer extends AppCompatActivity {

    private MKPlayer player;
    private boolean mShouldStop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        player=new MKPlayer(this);

        trackProgress();

        player.onComplete(new Runnable() {
            @Override
            public void run() {
                //callback when video is finish
                Toast.makeText(getApplicationContext(), "video play completed",Toast.LENGTH_SHORT).show();
            }
        }).onInfo(new MKPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                switch (what) {
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //do something when buffering start
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //do something when buffering end
                        break;
                    case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                        //download speed
                        break;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        //do something when video rendering
                        break;
                }
            }
        }).onError(new MKPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                Toast.makeText(getApplicationContext(), "video play error",Toast.LENGTH_SHORT).show();
            }
        });



        player.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {
              //  String url = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
                String url = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8";
                player.play(url);
                player.setTitle(url);


            }

            @Override
            public void onPreviousClick() {
               String url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
              //  String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4";

                //Todo self link to inset and play video like(http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4)
//                String url = ((EditText) findViewById(R.id.et_url)).getText().toString();
//                MKPlayerActivity.configPlayer(videoplayer.this).setTitle(url).play(url);


            }
        });
        String url=getIntent().getStringExtra("url");
        player.play(url);
        player.setTitle(url);

    }

    //Todo time scedule start time
    private void trackProgress() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!mShouldStop) {
//                    if (player != null && player.isPlaying()) {
//                        if (player.getCurrentPosition() >= 10000) {
//                          //  player.stopPlayback();
//                            mShouldStop = true;
//
//                        }
//                        try {
//                            Thread.sleep(500);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }).start();



    }

    /**
     * Dispatch onPause() to fragments.
     */




    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {

            player.onPause();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);

        }
    }


    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onPause();
        }

    }
}
