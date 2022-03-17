package com.example.numbercharades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

import org.w3c.dom.Text;

public class PlayArea extends AppCompatActivity {
    int count = 59;
    int score = 0;
    int correctValue = 1;

    int[] buttonValueArray = {0,0,0,0};

    private TextView countdown;
    private TextView guess;
    private TextView increment;
    private TextView correctIncorrect;
    private TextView availableHints;
    private TextView hintDisplay;

    private Button playAreaButton1;
    private Button playAreaButton2;
    private Button playAreaButton3;
    private Button playAreaButton4;
    private Button hintMenuButton;

    final Random random = new Random();

    Thread thread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_area);

        countdown = (TextView) findViewById(R.id.textView3);
        guess = (TextView) findViewById(R.id.textView4);
        correctIncorrect = (TextView) findViewById(R.id.textView7);
        increment = (TextView) findViewById(R.id.textView8);
        availableHints = (TextView) findViewById(R.id.textView5);
        hintDisplay = (TextView) findViewById(R.id.textView6);

        playAreaButton1= (Button) findViewById(R.id.button7);
        playAreaButton2= (Button) findViewById(R.id.button8);
        playAreaButton3= (Button) findViewById(R.id.button9);
        playAreaButton4= (Button) findViewById(R.id.button10);

        hintMenuButton = (Button) findViewById(R.id.button11);


        thread = new Thread(){
            @Override
            public void run(){
                try{
                    while(!thread.isInterrupted() && count > 0){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                countdown.setText(String.valueOf(count));
                                count--;
                            }
                        });

                        // everything else goes here

                        increment.setVisibility(View.INVISIBLE);
                        correctIncorrect.setVisibility(View.INVISIBLE);

                        playAreaButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checker(buttonValueArray[0]);
                                generate();
                            }
                        });

                        playAreaButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checker(buttonValueArray[1]);
                                generate();
                            }
                        });

                        playAreaButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checker(buttonValueArray[2]);
                                generate();

                            }
                        });

                        playAreaButton4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checker(buttonValueArray[3]);
                                generate();

                            }
                        });

                        hintMenuButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                hintOrMenu();
                            }
                        });

                    }
                    gameOver();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        generate();
        thread.start();
    }

    public void generate(){
        if (count >= 5){
            int lowerBound = random.nextInt(0 + 100) - 100;
            int upperBound = random.nextInt(100 - 0) + 0;
            int correctIndex = random.nextInt(3);

            for(int i = 0; i < 4; i++){
                buttonValueArray[i] = random.nextInt(upperBound-lowerBound)+lowerBound;
            }
            //correctValue = buttonValueArray[correctIndex];
            correctValue = buttonValueArray[0];

            playAreaButton1.setText(String.valueOf(buttonValueArray[0]));
            guess.setText(String.format("Guess the number between %d and %d", lowerBound, upperBound));
            playAreaButton2.setText(String.valueOf(buttonValueArray[1]));
            playAreaButton3.setText(String.valueOf(buttonValueArray[2]));
            playAreaButton4.setText(String.valueOf(buttonValueArray[3]));

        }else{
            guess.setText("Game Over");
        }

    }


    public void checker(int buttonValue){
        if(buttonValue == correctValue){
            count+= 6;
            score++;
            increment.setText("+5");
            increment.setTextColor(Color.parseColor("#32CD32"));
            increment.setVisibility(View.VISIBLE);
            correctIncorrect.setText(String.format("Correct: The number is %d", correctValue));
            correctIncorrect.setVisibility(View.VISIBLE);
        }else{
            if(count<=5){
                thread.interrupt();
                countdown.setText("0");
                count-=5;
                gameOver();
            }else{
                count-= 4;
                increment.setText("-5");
                increment.setTextColor(Color.parseColor("#FF0000"));
                increment.setVisibility(View.VISIBLE);
                correctIncorrect.setText(String.format("Incorrect: The number is %d", correctValue));
                correctIncorrect.setVisibility(View.VISIBLE);
            }
        }

    }

    public void hintOrMenu(){
        if(count > 0){
            hintDisplay.setText("69");
            hintDisplay.setVisibility(View.VISIBLE);
        }else{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
    }

    public void gameOver(){
        playAreaButton1.setVisibility(View.INVISIBLE);
        playAreaButton2.setVisibility(View.INVISIBLE);
        playAreaButton3.setVisibility(View.INVISIBLE);
        playAreaButton4.setVisibility(View.INVISIBLE);
        availableHints.setVisibility(View.INVISIBLE);
        hintDisplay.setVisibility(View.INVISIBLE);
        hintMenuButton.setText("Main menu");
        guess.setText("Game Over");
        correctIncorrect.setText(String.format("Score: %d",score));
        correctIncorrect.setVisibility(View.VISIBLE);

    }



}