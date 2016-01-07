package com.example.manuel.x_wingsescape;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer backgroundSound;
    MediaPlayer clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button easy = (Button) this.findViewById(R.id.easyMode);
        Button medium = (Button) this.findViewById(R.id.mediumMode);
        Button hard = (Button) this.findViewById(R.id.hardcoreMode);

        easy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.click_sound));
                clickSound.setVolume(90, 90);
                clickSound.start();

                Intent easyGame = new Intent(MainActivity.this, GameActivity.class);
                easyGame.putExtra("constObject", 12);
                easyGame.putExtra("constMonster", 6);
                easyGame.putExtra("mode", "easy");
                startActivity(easyGame);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.click_sound));
                clickSound.setVolume(90, 90);
                clickSound.start();

                Intent mediumGame = new Intent(MainActivity.this, GameActivity.class);
                mediumGame.putExtra("constObject", 6);
                mediumGame.putExtra("constMonster", 6);
                startActivity(mediumGame);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.click_sound));
                clickSound.setVolume(90, 90);
                clickSound.start();

                Intent hardcoreGame = new Intent(MainActivity.this, GameActivity.class);
                hardcoreGame.putExtra("constObject", 3);
                hardcoreGame.putExtra("constMonster", 6);
                startActivity(hardcoreGame);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        backgroundSound.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        backgroundSound.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundSound = MediaPlayer.create(this.getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.home));
        backgroundSound.setLooping(true);
        backgroundSound.setVolume(75, 75);
        backgroundSound.start();
    }
}
