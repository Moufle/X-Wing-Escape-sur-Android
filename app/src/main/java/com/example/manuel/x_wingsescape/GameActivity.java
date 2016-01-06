package com.example.manuel.x_wingsescape;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

public class GameActivity extends MainActivity {

    int[][] grid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        this.generate(15, 10);
    }

    public void generate(int rows, int columns) {
        grid = new int[rows][columns];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                grid[x][y] = 0;
            }
        }

        int persoX = (int) (Math.random() % 7 + 2);
        int persoY = (int) (Math.random() % 14 + 2);

        grid[persoX][persoY] = 1;

        grid[persoX + 1][persoY] = 4;
        grid[persoX][persoY + 1] = 4;
        grid[persoX - 1][persoY] = 4;
        grid[persoX][persoY - 1] = 4;
        grid[persoX + 1][persoY + 1] = 4;
        grid[persoX - 1][persoY - 1] = 4;
        grid[persoX + 1][persoY - 1] = 4;
        grid[persoX - 1][persoY + 1] = 4;

        for(int nbMonster = 5; nbMonster >= 0; nbMonster --) {
            int MonsterX;
            int MonsterY;

            MonsterX = (int) (Math.random() % rows);
            MonsterY = (int) (Math.random() % columns);

            if (grid[MonsterX][MonsterY] == 0) {
                grid[MonsterX][MonsterY] = 5;
            } else {
                nbMonster ++;
            }
        }

        for(int nbObject = 3; nbObject >= 0; nbObject --) {
            int ObjectX;
            int ObjectY;

            ObjectX = (int) (Math.random() % 7 + 2);
            ObjectY = (int) (Math.random() % 14 + 2);

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void update(int rows, int columns) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
               // trouver la bonne image view
                ImageView iv = null ; //...
                switch(grid[x][y]){
                    case 0 :
                        int caseID = getResources().getIdentifier("frame" + x + "_" + y, "id", getPackageName());
                        iv = (ImageView) findViewById(caseID);
                        iv.setImageDrawable(getResources().getDrawable(R.drawable.xwing, getApplicationContext().getTheme()));
                        break;
                    case 1 :
                        int viewID = getResources().getIdentifier("frame" + x + "_" + y, "id", getPackageName());
                        iv = (ImageView) findViewById(viewID);
                        iv.setImageDrawable(getResources().getDrawable(R.drawable.tie_fighter, getApplicationContext().getTheme()));
                        break;

                }
            }
        }
    }

    private void printFrames (int rows, int columns, int[][] grid, Random r) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = r.nextInt(2 + 1);
                System.out.print(" " + grid[i][j]);

            }

            System.out.println("");

        }
    }

}
