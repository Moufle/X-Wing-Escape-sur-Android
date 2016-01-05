package com.example.manuel.x_wingsescape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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

        backgroundSound = MediaPlayer.create(this.getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.home));
        backgroundSound.setLooping(true);
        backgroundSound.setVolume(75, 75);
        backgroundSound.start();

        Button easy = (Button) this.findViewById(R.id.easyMode);
        Button medium = (Button) this.findViewById(R.id.mediumMode);
        Button hard = (Button) this.findViewById(R.id.hardcoreMode);

        easy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.click_sound));
                clickSound.setVolume(90, 90);
                clickSound.start();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.click_sound));
                clickSound.setVolume(90, 90);
                clickSound.start();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.click_sound));
                clickSound.setVolume(90, 90);
                clickSound.start();
            }
        });
    }

}
