package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DictionarySelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_select);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_dictionary);

        Button primaryBtn = (Button)findViewById(R.id.primaryBtn);
        Button secondaryBtn = (Button)findViewById(R.id.secondaryBtn);
        Button higherBtn = (Button)findViewById(R.id.higherBtn);

        primaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionaryIntent = new Intent(getApplicationContext(), DictionaryActivity.class);
                startActivity(dictionaryIntent);
            }
        });

        secondaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionaryIntent = new Intent(getApplicationContext(), DictionaryActivity2.class);
                startActivity(dictionaryIntent);
            }
        });

        higherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionaryIntent = new Intent(getApplicationContext(), DictionaryActivity3.class);
                startActivity(dictionaryIntent);
            }
        });
    }
}