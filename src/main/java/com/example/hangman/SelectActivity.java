package com.example.hangman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SelectActivity extends AppCompatActivity {

    Spinner field;
    Spinner level;
    ArrayAdapter fieldAdapter;
    ArrayAdapter levelAdapter;
    String fieldSelected;
    String levelSelected;
    Button gameStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_logo);

        field = findViewById(R.id.spinnerField);
        level = findViewById(R.id.spinnerLevel);
        gameStart = findViewById(R.id.startGame);

        final String[] fieldData = getResources().getStringArray(R.array.fieldArray);
        fieldAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, fieldData);
        fieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        field.setAdapter(fieldAdapter);

        field.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fieldSelected = fieldData[position];
                Toast.makeText(getApplicationContext(),"난이도 :" + fieldSelected + "(이)가 선택되었습니다",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final String[] levelData = getResources().getStringArray(R.array.levelArray);
        levelAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, levelData);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(levelAdapter);

        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelSelected = levelData[position];
                Toast.makeText(getApplicationContext(),"난이도 :" + levelSelected + "(이)가 선택되었습니다",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        gameStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent gameIntent = new Intent(getApplicationContext(), GameActivity.class);
                gameIntent.putExtra("field",fieldSelected);
                gameIntent.putExtra("level",levelSelected);
                System.out.println("field"+fieldSelected);
                System.out.println("level"+levelSelected);

                startActivity(gameIntent);
            }
        });
    }

}
