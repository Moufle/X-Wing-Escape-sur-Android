package com.example.manuel.x_wingsescape;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

public class GameActivity extends MainActivity {

    int[][] grid = null;
    int constObject = 0;
    int constMonster = 0;

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

        int persoX = (int) (Math.random() * 12 + 1);
        int persoY = (int) (Math.random() * 6 + 1);

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
        for(int nbMonster = constMonster; nbMonster >= 0; nbMonster --) {
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

        int constObjsct = this.constObject;
        for(int nbObject = constObjsct; nbObject >= 0; nbObject --) {
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
                        iv.setImageResource(R.drawable.case_xhdpi);
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
