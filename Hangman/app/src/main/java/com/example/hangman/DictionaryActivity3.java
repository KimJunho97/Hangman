package com.example.hangman;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DictionaryActivity3 extends AppCompatActivity {

    private static final String TABLE_WORDS_HIGHER = "Higher_Words";
    private static final String HIGHER_ID = "h_id";
    private static final String HIGHER_WORD = "h_word";

    private ListView listView;
    private ImageButton addWordButton;
    private ArrayAdapter<Word> adapter;
    private List<Word> words;
    AdapterView.OnItemClickListener onItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
            final Word word = words.get((int) l);
            AlertDialog.Builder builder = new AlertDialog.Builder(DictionaryActivity3.this);
            builder.setMessage("Do you really want to delete " + word.getWord()).setTitle("Delete word");
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DataBaseHelper db = new DataBaseHelper(DictionaryActivity3.this);
                    db.deleteHWord(word);
                    words.remove(word);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(DictionaryActivity3.this, "You deleted " + word.getWord(), Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton(android.R.string.no, null);
            builder.create().show();
        }
    };
    View.OnClickListener onAddWordButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final EditText wordInput = new EditText(DictionaryActivity3.this);

            AlertDialog.Builder builder = new AlertDialog.Builder(DictionaryActivity3.this);
            builder.setTitle("Add word");
            builder.setView(wordInput);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DataBaseHelper db = new DataBaseHelper(DictionaryActivity3.this);
                    Word word = new Word(wordInput.getText().toString());
                    if (db.addWord(word,HIGHER_WORD,TABLE_WORDS_HIGHER)) {
                        words.add(word);
                        Collections.sort(words, new Comparator<Word>() {
                            @Override
                            public int compare(Word w1, Word w2) {
                                return w1.getWord().compareToIgnoreCase(w2.getWord());
                            }
                        });
                        adapter.notifyDataSetChanged();
                        Toast.makeText(DictionaryActivity3.this, "You added " + word.getWord(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DictionaryActivity3.this, word.getWord() + " already exists!", Toast.LENGTH_LONG).show();
                    }
                }
            });
            builder.setNegativeButton(android.R.string.cancel, null);
            builder.create().show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary3);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_higher);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_higher);

        DataBaseHelper helper = new DataBaseHelper(this);
        words = helper.getAll(TABLE_WORDS_HIGHER,HIGHER_ID,HIGHER_WORD);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);

        listView = (ListView) findViewById(R.id.wordsListView);
        addWordButton = (ImageButton) findViewById(R.id.addWordImageButton);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClicked);

        addWordButton.setOnClickListener(onAddWordButtonClicked);
    }
}