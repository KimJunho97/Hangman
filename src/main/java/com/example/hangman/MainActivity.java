package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button newGameButton;
    Button dictionaryButton;
    View.OnClickListener onNewGameButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gameIntent = new Intent(MainActivity.this, SelectActivity.class);
            startActivity(gameIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameButton = (Button) findViewById(R.id.newGameButton);
        dictionaryButton = (Button) findViewById(R.id.dictionaryButton);

        newGameButton.setOnClickListener(onNewGameButtonClicked);
        dictionaryButton.setOnClickListener(onDictionaryButtonClicked);
    }
}
