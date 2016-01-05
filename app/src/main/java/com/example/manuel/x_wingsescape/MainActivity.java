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

public class MainActivity extends AppCompatActivity {

    MediaPlayer objPlayer;
    private static final String LOGCAT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOGCAT, "Service Started!");
        objPlayer = MediaPlayer.create(this.getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.home));
        objPlayer.setLooping(true);
        objPlayer.start();
    }
}
