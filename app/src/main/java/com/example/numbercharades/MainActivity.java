package com.example.numbercharades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button startGameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = (Button) findViewById(R.id.button1);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStartGame();
            }
        });

    }
    public void openStartGame(){
        Intent intentStartGame = new Intent(this,StartGame.class);
        startActivity(intentStartGame);
    }








}