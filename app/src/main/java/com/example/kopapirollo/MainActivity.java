package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewPlayer;
    private ImageView imageViewComp;
    private TextView textViewResult;
    private Button buttonRock;
    private Button buttonPaper;
    private Button buttonScissor;
    private AlertDialog.Builder alertGameOver;
    private int randomNumber;
    private int compPoints;
    private int playerPoints;
    public void init(){
        imageViewPlayer = findViewById(R.id.imageViewPLayer);
        imageViewComp = findViewById(R.id.imageViewComp);
        textViewResult = findViewById(R.id.textViewResult);
        buttonRock = findViewById(R.id.buttonRock);
        buttonPaper = findViewById(R.id.buttonPaper);
        buttonScissor = findViewById(R.id.buttonScissor);
        compPoints = 0;
        playerPoints = 0;
        alertGameOver = new AlertDialog.Builder(MainActivity.this);
        alertGameOver.setMessage("Új játék kezdése")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newGame();
                    }
                })
                .setCancelable(false)
                .create();
    }

    public void compGame(){
        Random rnd = new Random();
        randomNumber = rnd.nextInt(3);

        switch (randomNumber){
            case 0:
                imageViewComp.setImageResource(R.drawable.rock);
                break;
            case 1:
                imageViewComp.setImageResource(R.drawable.paper);
                break;
            case 2:
                imageViewComp.setImageResource(R.drawable.scissors);
                break;
        }
    }

    public void result(int playerNumber){
        if (randomNumber == 0 && playerNumber == 1){
            playerPoints++;
            Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
        } else if (randomNumber == 0 && playerNumber == 2) {
            compPoints++;
            Toast.makeText(this, "A gép nyert", Toast.LENGTH_SHORT).show();
        } else if (randomNumber == 1 && playerNumber == 0) {
            compPoints++;
            Toast.makeText(this, "A gép nyert", Toast.LENGTH_SHORT).show();
        } else if (randomNumber == 1 && playerNumber == 2) {
            playerPoints++;
            Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
        } else if (randomNumber == 2 && playerNumber == 0) {
            playerPoints++;
            Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
        } else if (randomNumber == 2 && playerNumber == 1) {
            compPoints++;
            Toast.makeText(this, "A gép nyert", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        }

        textViewResult.setText("Eredmény: Ember: " + String.valueOf(playerPoints) + " Gép: " + String.valueOf(compPoints));

        if (playerPoints == 3){
            alertGameOver.setTitle("Győzelem").create();
            alertGameOver.show();
        } else if(compPoints == 3){
            alertGameOver.setTitle("Vesztettél").create();
            alertGameOver.show();
        }
    }

    public void newGame(){
        compPoints = 0;
        playerPoints = 0;
        imageViewComp.setImageResource(R.drawable.rock);
        imageViewPlayer.setImageResource(R.drawable.rock);
        textViewResult.setText("Eredmény: Ember: " + String.valueOf(playerPoints) + " Gép: " + String.valueOf(compPoints));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlayer.setImageResource(R.drawable.rock);
                compGame();
                result(0);
            }
        });

        buttonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlayer.setImageResource(R.drawable.paper);
                compGame();
                result(1);
            }
        });

        buttonScissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlayer.setImageResource(R.drawable.scissors);
                compGame();
                result(2);
            }
        });
    }
}