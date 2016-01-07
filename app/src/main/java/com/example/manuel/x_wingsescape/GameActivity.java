package com.example.manuel.x_wingsescape;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int[][] grid = null;
    int constObject = 0;
    int constMonster = 0;
    MediaPlayer GameSound;
    float x1,x2;
    float y1, y2;
    float diffx, diffy;
    int persoX;
    int persoY;
    int nbcoups = 0;
    double[] Vecteur = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                constObject = 5;
                constMonster = 5;
            } else {
                constObject = extras.getInt("constObject");
                constMonster = extras.getInt("constMonster");
            }
        } else {
            constObject = (int) savedInstanceState.getSerializable("nbObject");
            constMonster = (int) savedInstanceState.getSerializable("nbMonster");
        }

        this.generate(15, 10);
    }

    public void generate(int rows, int columns) {
        grid = new int[rows][columns];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                grid[x][y] = 0;
            }
        }

        persoX = (int) (Math.random() * 12 + 1);
        persoY = (int) (Math.random() * 6 + 1);

        grid[persoX][persoY] = 1;

        grid[persoX + 1][persoY] = 4;
        grid[persoX][persoY + 1] = 4;
        grid[persoX - 1][persoY] = 4;
        grid[persoX][persoY - 1] = 4;
        grid[persoX + 1][persoY + 1] = 4;
        grid[persoX - 1][persoY - 1] = 4;
        grid[persoX + 1][persoY - 1] = 4;
        grid[persoX - 1][persoY + 1] = 4;

        int constMonster = this.constMonster;
        for(int nbMonster = constMonster; nbMonster > 0; nbMonster --) {
            int MonsterX;
            int MonsterY;

            MonsterX = (int) (Math.random() * rows);
            MonsterY = (int) (Math.random() * columns);

            if (grid[MonsterX][MonsterY] == 0) {
                grid[MonsterX][MonsterY] = 5;
            } else {
                nbMonster ++;
            }
        }

        int constObject = this.constObject;
        for(int nbObject = constObject; nbObject > 0; nbObject --) {
            int ObjectX;
            int ObjectY;

            ObjectX = (int) (Math.random() * 12 + 1);
            ObjectY = (int) (Math.random() * 6 + 1);

            if (grid[ObjectX][ObjectY] == 0) {
                grid[ObjectX][ObjectY] = 10;
            } else {
                nbObject ++;
            }
        }

        grid[persoX + 1][persoY] = 0;
        grid[persoX][persoY + 1] = 0;
        grid[persoX - 1][persoY] = 0;
        grid[persoX][persoY - 1] = 0;
        grid[persoX + 1][persoY + 1] = 0;
        grid[persoX - 1][persoY - 1] = 0;
        grid[persoX + 1][persoY - 1] = 0;
        grid[persoX - 1][persoY + 1] = 0;

        this.update(rows, columns);
    }

    private void update(int rows, int columns) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                ImageView iv;
                switch(grid[x][y]){
                    case 0 :
                        int caseID = getResources().getIdentifier("frame" + (x+1) + "_" + (y+1), "id", getPackageName());
                        iv = (ImageView) findViewById(caseID);
                        iv.setImageResource(R.drawable.ncase_xhdpi);
                        break;
                    case 1 :
                        int xwingID = getResources().getIdentifier("frame" + (x+1) + "_" + (y+1), "id", getPackageName());
                        iv = (ImageView) findViewById(xwingID);
                        iv.setImageResource(R.drawable.xwing);
                        break;
                    case 5 :
                        int tiefighterID = getResources().getIdentifier("frame" + (x+1) + "_" + (y+1), "id", getPackageName());
                        iv = (ImageView) findViewById(tiefighterID);
                        iv.setImageResource(R.drawable.tie_fighter);
                        break;
                    case 10 :
                        int objectID = getResources().getIdentifier("frame" + (x+1) + "_" + (y+1), "id", getPackageName());
                        iv = (ImageView) findViewById(objectID);
                        iv.setImageResource(R.drawable.debris);
                        break;

                }
            }
        }
    }

    private void move(int rows, int columns, String move) {

        int temPositionX = persoX;
        int temPositionY = persoY;
        if (move == "up") {
            grid[persoX - 1][persoY] = grid[persoX][persoY] + grid[persoX - 1][persoY];
            persoX = persoX - 1;
            grid[temPositionX][persoY] = 0;
        } else if (move == "down") {
            grid[persoX + 1][persoY] = grid[persoX][persoY] + grid[persoX + 1][persoY];
            persoX = persoX + 1;
            grid[temPositionX][persoY] = 0;
        } else if (move == "left") {
            grid[persoX][persoY - 1] = grid[persoX][persoY] + grid[persoX][persoY - 1];
            persoY = persoY - 1;
            grid[persoX][temPositionY] = 0;
        } else if (move == "right") {
            grid[persoX][persoY + 1] = grid[persoX][persoY] + grid[persoX][persoY + 1];
            persoY = persoY + 1;
            grid[persoX][temPositionY] = 0;
        }


        this.chase(15, 10);

        nbcoups++;

        this.update(rows, columns);
    }

    private void chase(int rows, int columns){

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (grid[x][y] == 5) {

                    Vecteur = this.c2r(persoX - x, persoY - y);

                    if(Vecteur[1] == (Math.PI / 4)
                            || Vecteur[1] == (- Math.PI / 4)
                            || Vecteur[1] == ((3 * Math.PI) / 4)
                            || Vecteur[1] == ((-3 * Math.PI) / 4)){

                        Vecteur[1] += 0.5;
                    }

                    if (((Vecteur[1] > (-Math.PI / 4)) && (Vecteur[1] <= 0)) || ((Vecteur[1] < (Math.PI / 4)) && (Vecteur[1] >= 0))){
                        grid[x + 1][y] = grid[x + 1][y] + grid[x][y];
                        grid[x][y] = 0;
                    } else if ((Vecteur[1] > ((-3 * Math.PI) / 4)) && (Vecteur[1] < (-Math.PI / 4))){
                        grid[x][y - 1] = grid[x][y - 1] + grid[x][y];
                        grid[x][y] = 0;
                    } else if (((Vecteur[1] > ((3 * Math.PI) / 4)) && (Vecteur[1] <= Math.PI)) || ((Vecteur[1] < ((-3 * Math.PI) / 4)) && (Vecteur[1] >= (-Math.PI)))){
                        grid[x - 1][y] = grid[x - 1][y] + grid[x][y];
                        grid[x][y] = 0;
                    } else if ((Vecteur[1] > ( Math.PI / 4)) && (Vecteur[1] < ((3 * Math.PI) / 4))){
                        grid[x][y + 1] = grid[x][y + 1] + grid[x][y];
                        grid[x][y] = 0;
                    }

                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent touchEvent)
    {
        switch (touchEvent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                diffx = x2-x1;
                diffy = y2-y1;

                //if left to right sweep event on screen
                if (x1 < x2 && Math.abs(diffy) < Math.abs(diffx)) {
                    if (persoY < 9) {
                        if (grid[persoX][persoY + 1] != 10) {
                            this.move(15, 10, "right");
                        }
                    }
                }

                // if right to left sweep event on screen
                if (x2 < x1 && Math.abs(diffy) < Math.abs(diffx))
                {
                    if (persoY > 0) {
                        if (grid[persoX][persoY - 1] != 10) {
                            this.move(15, 10, "left");
                        }
                    }
                }

                // if UP to Down sweep event on screen
                if (y1 < y2 && Math.abs(diffx) < Math.abs(diffy))
                {
                    if (persoX < 14) {
                        if (grid[persoX + 1][persoY] != 10) {
                            this.move(15, 10, "down");
                        }
                    }
                }

                //if Down to UP sweep event on screen
                if (y2 < y1 && Math.abs(diffx) < Math.abs(diffy))
                {
                    if (persoX > 0) {
                        if (grid[persoX - 1][persoY] != 10) {
                            this.move(15, 10, "up");
                        }
                    }
                }
                break;
            }
        }
        return false;
    }

    private double[] c2r(double x, double y) {
        double res[] = new double[2];
        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double t = Math.atan2(y, x);
        res[0] = r;
        res[1] = t;
        return res;
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameSound = MediaPlayer.create(this.getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.game));
        GameSound.setLooping(true);
        GameSound.setVolume(75, 75);
        GameSound.start();
    }

    @Override
        protected void onPause() {
        super.onPause();
        GameSound.stop();
    }
}
