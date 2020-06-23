package com.example.hangman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hangman-dictionary.db";

    private static final String TABLE_WORDS_PRIMARY = "Primary_Words";
    private static final String PRIMARY_ID = "p_id";
    private static final String PRIMARY_WORD = "p_word";
    //private static final String PRIMARY_MEANING = "p_meaning";

    private static final String TABLE_WORDS_SECONDARY = "Secondary_Words";
    private static final String SECONDARY_ID = "s_id";
    private static final String SECONDARY_WORD = "s_word";
    //private static final String SECONDARY_MEANING = "s_meaning";

    private static final String TABLE_WORDS_HIGHER = "Higher_Words";
    private static final String HIGHER_ID = "h_id";
    private static final String HIGHER_WORD = "h_word";
    //private static final String HIGHER_MEANING = "h_meaning";

    private static final String CREATE_TABLE_WORDS_P = "CREATE TABLE IF NOT EXISTS " + TABLE_WORDS_PRIMARY + "('"
            + PRIMARY_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, '"
            + PRIMARY_WORD + "' TEXT NOT NULL UNIQUE);";

    private static final String CREATE_TABLE_WORDS_S = "CREATE TABLE IF NOT EXISTS " + TABLE_WORDS_SECONDARY + "('"
            + SECONDARY_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, '"
            + SECONDARY_WORD + "' TEXT NOT NULL UNIQUE);";

    private static final String CREATE_TABLE_WORDS_H = "CREATE TABLE IF NOT EXISTS " + TABLE_WORDS_HIGHER + "('"
            + HIGHER_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, '"
            + HIGHER_WORD + "' TEXT NOT NULL UNIQUE);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Cursor cursor_p = null;
        try {
            db.execSQL(CREATE_TABLE_WORDS_P);
            cursor_p = db.query(TABLE_WORDS_PRIMARY, new String[]{"*"}, null, null, null, null, null);
            if (!cursor_p.moveToNext()) {
                String[] words =
                        ("ORANGE=오렌지,KILL=죽이다,HEAT=떄리다,SHORT=짧은,TRICK=속임수,"+
                                "BELLS=종소리,NICE=좋은,SAND=모래,ARM=팔,NUTTY=견과류,"+
                                "CLIP=클립,DRIVING=운전하는,FLY=날다,CHEAT=속임수를쓰다,WOOD=나무,"+
                                "NUMBER=숫자,LAND=땅,SIMPLE=단순한,HORSE=말,BADGE=뱃지,"+
                                "ROUTE=길,CAN=할수있다,TICKET=표,DEER=사슴,CANNON=대포,"+
                                "HOUSE=집,PART=부분,LEAN=기울다,MAN=남자,TOE=발가락,"+
                                "ROAD=도로,GIRL=소녀,GHOST=귀신,FROG=개구리,SAD=슬픈,"+
                                "CUTE=귀여운,LEG=다리,ZIP=집,STAGE=단계,DOLL=인형").split(",");

                String query = "INSERT INTO " + TABLE_WORDS_PRIMARY + " (" + PRIMARY_WORD + ") VALUES ('" +
                        (StringUtil.join("'),('", Arrays.asList(words))) + "')";

                db.execSQL(query);
            }

        } catch (SQLException e) {
            logException(e);
        } finally {
            if (cursor_p != null) {
                cursor_p.close();
            }
        }

        Cursor cursor_s = null;
        try {
            db.execSQL(CREATE_TABLE_WORDS_S);
            cursor_s = db.query(TABLE_WORDS_SECONDARY, new String[]{"*"}, null, null, null, null, null);
            if (!cursor_s.moveToNext()) {
                String[] words =
                        ("HOLIDAY=휴일,BULB=전구,PALTRY=보잘것없는,FLESH=살찌다,STOVE=난로,"+
                                "CONTINUE=계속하다,TYPICAL=전형적인,DECORATE=장식을하다,REGRET=후회하다,TEACHING=가르침,"+
                                "DELIGHT=기쁨,REPLY=응답하다,QUESTION=질문,MATE=친구,THAW=해동하다," +
                                "LEGAL=합법의,SOOTHE=달래다,STEEP=가파른,SHADE=그늘,NECESSARY=필요한,"+
                                "ELBOW=팔꿈치,TEETH=치아,COUNTRY=나라,LARGE=큰,SLEEPY=졸린,"+
                                "CAUSE=야기하다,REPLACE=대체하다,MIDDLE=중간의,AIRPORT=공항,BRAKE=멈추다").split(",");

                String query = "INSERT INTO " + TABLE_WORDS_SECONDARY + " (" + SECONDARY_WORD + ") VALUES ('" +
                        (StringUtil.join("'),('", Arrays.asList(words))) + "')";

                db.execSQL(query);
            }

        } catch (SQLException e) {
            logException(e);
        } finally {
            if (cursor_s != null) {
                cursor_s.close();
            }
        }

        Cursor cursor_h = null;
        try {
            db.execSQL(CREATE_TABLE_WORDS_H);
            cursor_h = db.query(TABLE_WORDS_HIGHER, new String[]{"*"}, null, null, null, null, null);
            if (!cursor_h.moveToNext()) {
                String[] words =
                        ("HEARTBREAKING=애달픈,DISGUSTING=역겨운,DISGUSTED=역겨운,CONFUSE=혼란시키다,TRUTHFUL=진실된," +
                                "SUSPECT=의심을품다,EXPLODE=폭발시키다,ABUSIVE=욕같은,SPECTACULAR=굉장한,CABBAGE=양배추," +
                                "UNKNOWN=미확인의,VAGABOND=방랑자,VENGEFUL=복수심에가득찬,SECONDHAND=중고의,SQUEAK=꽥꽥거리다,"+
                                "HYPNOTIC=최면술,TRICKY=교활한,INFAMOUS=악명높은,HESITANT=망설임,STRING=끈을매다,"+
                                "PRODUCTIVE=생산적인,ADHESIVE=접착제,FLIPPANT=경솔한,THROAT=목구멍,FESTIVE=축제의,"+
                                "GLAMOROUS=매력있는").split(",");

                String query = "INSERT INTO " + TABLE_WORDS_HIGHER + " (" + HIGHER_WORD + ") VALUES ('" +
                        (StringUtil.join("'),('", Arrays.asList(words))) + "')";

                db.execSQL(query);
            }

        } catch (SQLException e) {
            logException(e);
        } finally {
            if (cursor_h != null) {
                cursor_h.close();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Word getRandom(String field) {
        Cursor cursor = null;
        SQLiteDatabase db = null;

        String table = null;
        String id = null;
        String word = null;

        if(field.equalsIgnoreCase("primary")){
            table = TABLE_WORDS_PRIMARY;
            id = PRIMARY_ID;
            word = PRIMARY_WORD;
        }
        if(field.equalsIgnoreCase("secondary")){
            table = TABLE_WORDS_SECONDARY;
            id = SECONDARY_ID;
            word = SECONDARY_WORD;
        }
        if(field.equalsIgnoreCase("higher")){
            table = TABLE_WORDS_HIGHER;
            id = HIGHER_ID;
            word = HIGHER_WORD;
        }

        try {
            db = getReadableDatabase();
            cursor = db.query(table, new String[]{id, word}, null, null, null, null, "RANDOM()", "1");
            if (cursor.moveToNext()) {
                return getWord(cursor,id,word);
            }
            return null;
        } catch (SQLException e) {
            logException(e);
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    public List<Word> getAll() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            List<Word> words = new ArrayList<>();
            cursor = db.query(TABLE_WORDS_PRIMARY, new String[]{PRIMARY_ID, PRIMARY_WORD}, null, null, null, null, PRIMARY_WORD);
            while (cursor.moveToNext()) {
                words.add(getWord(cursor));
            }
            return words;
        } catch (SQLException e) {
            logException(e);
            return new ArrayList<>();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }
    public List<Word> getAll(String name, String id, String word) {

        String tableName= name;
        String tableId = id;
        String tableWord = word;

        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            List<Word> words = new ArrayList<>();
            cursor = db.query(tableName, new String[]{tableId, tableWord}, null, null, null, null, tableWord);
            while (cursor.moveToNext()) {
                words.add(getWord(cursor,id,word));
            }
            return words;
        } catch (SQLException e) {
            logException(e);
            return new ArrayList<>();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    public boolean addWord(Word word) {
        SQLiteDatabase db = null;
        try {
            if (word.getWord() == null || word.getWord().trim().isEmpty()) return false;
            word.setWord(word.getWord().trim().toUpperCase());
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(PRIMARY_WORD, word.getWord());
            db.insertOrThrow(TABLE_WORDS_PRIMARY, null, cv);
            return true;
        } catch (SQLException e) {
            logException(e);
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
    public boolean addWord(Word word, String words, String table) {
        SQLiteDatabase db = null;
        try {
            if (word.getWord() == null || word.getWord().trim().isEmpty()) return false;
            word.setWord(word.getWord().trim().toUpperCase());
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(words, word.getWord());
            db.insertOrThrow(table, null, cv);
            return true;
        } catch (SQLException e) {
            logException(e);
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void deleteWord(Word word) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.delete(TABLE_WORDS_PRIMARY, "_id = ?", new String[]{Integer.toString(word.getId())});
        } catch (SQLException e) {
            logException(e);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
    public void deleteWord(Word word,String name) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.delete(name, "_id = ?", new String[]{Integer.toString(word.getId())});
        } catch (SQLException e) {
            logException(e);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @NonNull
    private Word getWord(Cursor cursor, String id, String word) {
        return new Word(
                cursor.getInt(cursor.getColumnIndex(id)),
                cursor.getString(cursor.getColumnIndex(word))
        );
    }
    private Word getWord(Cursor cursor) {
        return new Word(
                cursor.getInt(cursor.getColumnIndex(PRIMARY_ID)),
                cursor.getString(cursor.getColumnIndex(PRIMARY_WORD))
        );
    }


    private void logException(SQLException e) {
        Log.e("SQLError", e.getMessage());
    }
}