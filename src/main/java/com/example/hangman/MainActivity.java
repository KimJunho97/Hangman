package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button tutorialButton;
    Button newGameButton;
    Button dictionaryButton;

    View.OnClickListener onTutorialButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent tutorialIntent = new Intent(MainActivity.this, TutorialActivity.class);
            startActivity(tutorialIntent);
        }
    };
    View.OnClickListener onNewGameButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gameIntent = new Intent(MainActivity.this, SelectActivity.class);
            startActivity(gameIntent);
        }
    };
    View.OnClickListener onDictionaryButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent dictionaryIntent = new Intent(MainActivity.this, DictionarySelectActivity.class);
            startActivity(dictionaryIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tutorialButton = (Button) findViewById(R.id.tutorialButton);
        newGameButton = (Button) findViewById(R.id.newGameButton);
        dictionaryButton = (Button) findViewById(R.id.dictionaryButton);

        tutorialButton.setOnClickListener(onTutorialButtonClicked);
        newGameButton.setOnClickListener(onNewGameButtonClicked);
        dictionaryButton.setOnClickListener(onDictionaryButtonClicked);
    }
}