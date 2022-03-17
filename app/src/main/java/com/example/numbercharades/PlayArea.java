package com.example.numbercharades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PlayArea extends AppCompatActivity {
    int count = 60;
    private Button PlayAreaButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_area);

        final TextView countdown = (TextView) findViewById(R.id.textView3);
        final TextView guess = (TextView) findViewById(R.id.textView4);

        PlayAreaButton1= (Button) findViewById(R.id.button7);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count--;
                                countdown.setText(String.valueOf(count));
                            }
                        });

                        // everything else goes here

                        PlayAreaButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count+= 5;
                            }
                        });







                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

    }









}