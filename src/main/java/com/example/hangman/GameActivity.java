package com.example.hangman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private static final int EASY_TURNS = 10;
    private static final int MEDIUM_TURNS = 7;
    private static final int DIFFICULT_TURNS = 5;

    private TextView fieldlevelTextView;
    private TextView wordTextView;
    private ImageView hangmanImage;
    private TextView leftGuessesTextView;
    private TextView usedCharactersTextView;
    private EditText characterEditText;
    private Button guessButton;
    private String field;
    private String level;
    private int max_opportunity;

    String get_word;
    String words[];
    String word_chosed;
    String mean_chosed;

    private DataBaseHelper db = new DataBaseHelper(this);

    private char[] chars;
    private List<Character> used = new ArrayList<>();
    private List<Character> wrong = new ArrayList<>();
    private State state = State.PLAYING;
    View.OnClickListener onGuessButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (state != State.PLAYING) return;
            String character = characterEditText.getText().toString().trim().toUpperCase();
            if (character.isEmpty()) return;
            char c = character.charAt(0);
            selectChar(c);
            updateDisplay();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_logo);

        fieldlevelTextView = (TextView) findViewById(R.id.fieldlevel);
        wordTextView = (TextView) findViewById(R.id.wordTextView);
        hangmanImage = (ImageView) findViewById(R.id.hangmanImageView);
        leftGuessesTextView = (TextView) findViewById(R.id.leftGuessesTextView);
        usedCharactersTextView = (TextView) findViewById(R.id.usedCharactersTextView);
        characterEditText = (EditText) findViewById(R.id.characterEditText);
        guessButton = (Button) findViewById(R.id.guessButton);


        //SelectActuvuty로부터 분야,레벨 받아오기
        Intent gameintent = getIntent();
        field = gameintent.getStringExtra("field");
        level = gameintent.getStringExtra("level");

        fieldlevelTextView.setText("분야 :" + field + "\n" + "난이도 :" +  level + "\n" );


        if(level.equalsIgnoreCase("Easy")){
            max_opportunity = EASY_TURNS;
        }
        if(level.equalsIgnoreCase("Medium")){
            max_opportunity = MEDIUM_TURNS;
        }
        if(level.equalsIgnoreCase("Difficult")){
            max_opportunity = DIFFICULT_TURNS;
        }

        guessButton.setOnClickListener(onGuessButtonClicked);

        Word word = db.getRandom(field);

        get_word = word.getWord();
        words = get_word.split("=");
        word_chosed = words[0];
        mean_chosed = words[1];
        chars = word_chosed.toUpperCase().toCharArray();

        selectChar(chars[0]);
        selectChar(chars[chars.length - 1]);

        updateDisplay();
    }

    private void selectChar(char character) {
        if (state != State.PLAYING ||
                !isAlphabetic(character)) return;

        if (used.contains(character)) {
            Toast toast = Toast.makeText(this, character + " is already used!", Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.BLACK);
            toast.show();
            return;
        }

        used.add(character);
        boolean charUsed = false;
        boolean solved = true;

        for (char c : chars) {
            if (c == character) {
                charUsed = true;
            }
            if (!used.contains(c)) {
                solved = false;
            }
        }
        if (!charUsed)
            wrong.add(character);
        if (solved) {
            state = State.WON;
            Toast toast = Toast.makeText(this, "You won!" + "\n단어 :" + word_chosed + " 뜻 :" + mean_chosed, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.BLACK);
            toast.show();

        } else if (getTriesLeft() == 0) {
            state = State.LOST;
            Toast toast = Toast.makeText(this, "You lost!" + "\n단어 :" + word_chosed + " 뜻 :" + mean_chosed, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.BLACK);
            toast.show();
        }
    }

    private void updateDisplay() {
        wordTextView.setText(getDisplayedWord());
        characterEditText.setText("");
        usedCharactersTextView.setText(StringUtil.join(",", used));
        leftGuessesTextView.setText(String.valueOf(getTriesLeft()));
        leftGuessesTextView.setTextColor(Color.WHITE);
        setHangmanImage();
    }

    private int getTriesLeft() {
        return max_opportunity - wrong.size();
    }

    private String getDisplayedWord() {
        if (state != State.PLAYING) {
            return StringUtil.join(" ", chars);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = chars.length - 1; i <= j; i++) {
            char c = chars[i];
            if (!isAlphabetic(c) || used.contains(c)) {
                sb.append(c);
            } else {
                sb.append('_');
            }
            if (i != j) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    private void setHangmanImage() {
        int triesLeft = getTriesLeft();
        int resource = R.drawable.hangman0;

        if(level.equalsIgnoreCase("Easy")){
            switch (triesLeft) {
                case 0:
                    resource = R.drawable.hangman10;
                    break;
                case 1:
                    resource = R.drawable.hangman9;
                    break;
                case 2:
                    resource = R.drawable.hangman8;
                    break;
                case 3:
                    resource = R.drawable.hangman7;
                    break;
                case 4:
                    resource = R.drawable.hangman6;
                    break;
                case 5:
                    resource = R.drawable.hangman5;
                    break;
                case 6:
                    resource = R.drawable.hangman4;
                    break;
                case 7:
                    resource = R.drawable.hangman3;
                    break;
                case 8:
                    resource = R.drawable.hangman2;
                    break;
                case 9:
                    resource = R.drawable.hangman1;
                    break;
                case 10:
                    resource = R.drawable.hangman0;
                    break;
                default:
                    resource = R.drawable.hangman10;
                    break;
            }
        }

        if(level.equalsIgnoreCase("Medium")){
            switch (triesLeft) {
                case 0:
                    resource = R.drawable.hangman10;
                    break;
                case 1:
                    resource = R.drawable.hangman9;
                    break;
                case 2:
                    resource = R.drawable.hangman7;
                    break;
                case 3:
                    resource = R.drawable.hangman6;
                    break;
                case 4:
                    resource = R.drawable.hangman4;
                    break;
                case 5:
                    resource = R.drawable.hangman2;
                    break;
                case 6:
                    resource = R.drawable.hangman1;
                    break;
                case 7:
                    resource = R.drawable.hangman0;
                    break;
                default:
                    resource = R.drawable.hangman10;
                    break;
            }
        }

        if(level.equalsIgnoreCase("Difficult")){
            switch (triesLeft) {
                case 0:
                    resource = R.drawable.hangman10;
                    break;
                case 1:
                    resource = R.drawable.hangman8;
                    break;
                case 2:
                    resource = R.drawable.hangman6;
                    break;
                case 3:
                    resource = R.drawable.hangman2;
                    break;
                case 4:
                    resource = R.drawable.hangman1;
                    break;
                case 5:
                    resource = R.drawable.hangman0;
                    break;
                default:
                    resource = R.drawable.hangman10;
                    break;
            }
        }
        hangmanImage.setImageResource(resource);
    }

    private boolean isAlphabetic(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private enum State {
        WON, LOST, PLAYING
    }
}