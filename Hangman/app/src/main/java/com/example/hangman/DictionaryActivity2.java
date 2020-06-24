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

public class DictionaryActivity2 extends AppCompatActivity {

    private static final String TABLE_WORDS_SECONDARY = "Secondary_Words";
    private static final String SECONDARY_ID = "s_id";
    private static final String SECONDARY_WORD = "s_word";
    //private static final String SECONDARY_MEANING = "s_meaning";


    private ListView listView;
    private ImageButton addWordButton;
    private ArrayAdapter<Word> adapter;
    private List<Word> words;
    AdapterView.OnItemClickListener onItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
            final Word word = words.get((int) l);
            AlertDialog.Builder builder = new AlertDialog.Builder(DictionaryActivity2.this);
            builder.setMessage("Do you really want to delete " + word.getWord()).setTitle("Delete word");
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DataBaseHelper db = new DataBaseHelper(DictionaryActivity2.this);
                    db.deleteSWord(word);
                    words.remove(word);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(DictionaryActivity2.this, "You deleted " + word.getWord(), Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton(android.R.string.no, null);
            builder.create().show();
        }
    };
    View.OnClickListener onAddWordButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final EditText wordInput = new EditText(DictionaryActivity2.this);

            AlertDialog.Builder builder = new AlertDialog.Builder(DictionaryActivity2.this);
            builder.setTitle("Add word");
            builder.setView(wordInput);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DataBaseHelper db = new DataBaseHelper(DictionaryActivity2.this);
                    Word word = new Word(wordInput.getText().toString());
                    if (db.addWord(word,SECONDARY_WORD,TABLE_WORDS_SECONDARY)) {
                        words.add(word);
                        Collections.sort(words, new Comparator<Word>() {
                            @Override
                            public int compare(Word w1, Word w2) {
                                return w1.getWord().compareToIgnoreCase(w2.getWord());
                            }
                        });
                        adapter.notifyDataSetChanged();
                        Toast.makeText(DictionaryActivity2.this, "You added " + word.getWord(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DictionaryActivity2.this, word.getWord() + " already exists!", Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_dictionary2);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_secondary);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_secondary);

        DataBaseHelper helper = new DataBaseHelper(this);
        words = helper.getAll(TABLE_WORDS_SECONDARY,SECONDARY_ID,SECONDARY_WORD);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);

        listView = (ListView) findViewById(R.id.wordsListView);
        addWordButton = (ImageButton) findViewById(R.id.addWordImageButton);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClicked);

        addWordButton.setOnClickListener(onAddWordButtonClicked);
    }
}