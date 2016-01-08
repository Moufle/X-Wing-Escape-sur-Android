package com.example.manuel.x_wingsescape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DefeatActivity extends AppCompatActivity {

    String mode = null;
    int constObject = 0;
    int constMonster = 0;
    int Totalscore = 0;
    int highScore = 0;
    MediaPlayer DefeatSound;
    MediaPlayer clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defeat);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                constObject = 5;
                constMonster = 5;
                mode = null;
                Totalscore = 0;
                highScore = 0;
            } else {
                constObject = extras.getInt("constObject");
                constMonster = extras.getInt("constMonster");
                mode = extras.getString("mode");
                Totalscore = extras.getInt("Totalscore");
                highScore = extras.getInt("highScore");
            }
        } else {
            constObject = (int) savedInstanceState.getSerializable("nbObject");
            constMonster = (int) savedInstanceState.getSerializable("nbMonster");
            mode = (String) savedInstanceState.getSerializable("mode");
            Totalscore = (int) savedInstanceState.getSerializable("Totalscore");
            highScore = (int) savedInstanceState.getSerializable("highScore");
        }

        TextView scoreText = (TextView) this.findViewById(R.id.scoreText);
        scoreText.setText("Score : " + Totalscore);

        TextView highscoreText = (TextView) this.findViewById(R.id.highscoreText);
        highscoreText.setText("Highscore : " + highScore);

        Button replay = (Button) this.findViewById(R.id.replay);

        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSound = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.click_sound));
                clickSound.setVolume(50, 50);
                clickSound.start();

                Intent restartGame = new Intent(DefeatActivity.this, GameActivity.class);
                restartGame.putExtra("constObject", constObject);
                restartGame.putExtra("constMonster", constMonster);
                restartGame.putExtra("mode", mode);
                startActivity(restartGame);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DefeatSound = MediaPlayer.create(this.getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.defeat));
        DefeatSound.setLooping(true);
        DefeatSound.setVolume(100, 100);
        DefeatSound.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        DefeatSound.stop();
    }
}
