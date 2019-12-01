package com.kris.yapanap.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kris.yapanap.R;
import com.kris.yapanap.util.Util;
import com.kris.yapanap.model.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //The SQL statement that creates the Hiragana Table.
        String CREATE_HIRAGANA_TABLE = "CREATE TABLE " + Util.HIRAGANA_TABLE + "(" +
                Util.HIRAGANA_ID + " INTEGER PRIMARY KEY, " +
                Util.HIRAGANA_NAME + " TEXT, " +
                Util.HIRAGANA_SYMBOL + " TEXT, " +
                Util.HIRAGANA_COLUMN + " TEXT, " +
                Util.HIRAGANA_USERNOTE + " TEXT" + ")";

        //The SQL statement that creates the Katakana Table.
        String CREATE_KATAKANA_TABLE = "CREATE TABLE " + Util.KATAKANA_TABLE + "(" +
                Util.KATAKANA_ID + " INTEGER PRIMARY KEY, " +
                Util.KATAKANA_NAME + " TEXT, " +
                Util.KATAKANA_SYMBOL + " TEXT, " +
                Util.KATAKANA_COLUMN + " TEXT, " +
                Util.KATAKANA_USERNOTE + " TEXT" + ")";

        //The SQL statement that creates the Kanji Table.
        String CREATE_KANJI_TABLE = "CREATE TABLE " + Util.KANJI_TABLE + "(" +
                Util.KANJI_ID + " INTEGER PRIMARY KEY, " +
                Util.KANJI_SYMBOL + " TEXT, " +
                Util.KANJI_STROKECOUNT + " NUMBER, " +
                Util.KANJI_JLPT + " NUMBER, " +
                Util.KANJI_GRADE + " NUMBER, " +
                Util.KANJI_MEANINGS + " TEXT, " +
                Util.KANJI_KUNREADING + " TEXT, " +
                Util.KANJI_ONREADING + " TEXT, " +
                Util.KANJI_USERNOTE + " TEXT" + ")";

        //Execute SQL Statements
        db.execSQL(CREATE_HIRAGANA_TABLE);
        db.execSQL(CREATE_KATAKANA_TABLE);
        db.execSQL(CREATE_KANJI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);
    }

    //add a hiragana
    public void addHiragana(Hiragana hiragana) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.HIRAGANA_NAME, hiragana.getName());
        values.put(Util.HIRAGANA_SYMBOL, hiragana.getSymbol());
        values.put(Util.HIRAGANA_COLUMN, hiragana.getColumn());
        values.put(Util.HIRAGANA_USERNOTE, hiragana.getUserNote());

        db.insert(Util.HIRAGANA_TABLE, null, values);
        Log.d("DBDebug", "addHiragana: " + hiragana.getName());
        db.close();
    }

    //add a katakana
    public void addKatakana(Katakana katakana) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KATAKANA_NAME, katakana.getName());
        values.put(Util.KATAKANA_SYMBOL, katakana.getSymbol());
        values.put(Util.KATAKANA_COLUMN, katakana.getColumn());
        values.put(Util.KATAKANA_USERNOTE, katakana.getUserNote());

        db.insert(Util.KATAKANA_TABLE, null, values);
        Log.d("DBDebug", "addKatakana: " + katakana.getName());
        db.close();
    }

    //add a kanji
    public void addKanji(Kanji kanji) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KANJI_SYMBOL, kanji.getSymbol());
        values.put(Util.KANJI_STROKECOUNT, kanji.getStrokeCount());
        values.put(Util.KANJI_GRADE, kanji.getGrade());
        values.put(Util.KANJI_JLPT, kanji.getJLPT());
        values.put(Util.KANJI_MEANINGS, Util.convertArrayToString(kanji.getMeanings()));
        values.put(Util.KANJI_KUNREADING, Util.convertArrayToString(kanji.getKunReadings()));
        values.put(Util.KANJI_ONREADING, Util.convertArrayToString(kanji.getOnReadings()));
        values.put(Util.KANJI_USERNOTE, kanji.getUserNote());

        db.insert(Util.KANJI_TABLE, null, values);
        Log.d("DBDebug", "addKanji: " + kanji.getSymbol());
        db.close();
    }

    //get a Hiragana
    public Hiragana getHiragana(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.HIRAGANA_TABLE, new String[]{Util.HIRAGANA_ID, Util.HIRAGANA_NAME,
                        Util.HIRAGANA_SYMBOL, Util.HIRAGANA_COLUMN, Util.HIRAGANA_USERNOTE},
                Util.HIRAGANA_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Hiragana hiragana = new Hiragana();
            hiragana.setId(Integer.parseInt(cursor.getString(0)));
            hiragana.setName(cursor.getString(1));
            hiragana.setSymbol(cursor.getString(2));
            hiragana.setColumn(cursor.getString(3));
            hiragana.setUserNote(cursor.getString(4));

            db.close();
            return hiragana;
        } else {
            db.close();
            return null;
        }
    }

    //get a Katakana
    public Katakana getKatakana(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.KATAKANA_TABLE, new String[]{Util.KATAKANA_ID, Util.KATAKANA_NAME,
                        Util.KATAKANA_SYMBOL, Util.KATAKANA_COLUMN, Util.KATAKANA_USERNOTE},
                Util.KATAKANA_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Katakana katakana = new Katakana();
            katakana.setId(Integer.parseInt(cursor.getString(0)));
            katakana.setName(cursor.getString(1));
            katakana.setSymbol(cursor.getString(2));
            katakana.setColumn(cursor.getString(3));
            katakana.setUserNote(cursor.getString(4));

            db.close();
            return katakana;
        } else {
            db.close();
            return null;
        }
    }

    //get a Kanji
    public Kanji getKanji(String symbol) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.KANJI_TABLE, new String[]{Util.KANJI_ID, Util.KANJI_SYMBOL,
                        Util.KANJI_STROKECOUNT, Util.KANJI_GRADE, Util.KANJI_JLPT,
                        Util.KANJI_MEANINGS, Util.KANJI_KUNREADING, Util.KANJI_ONREADING, Util.KANJI_USERNOTE},
                Util.KANJI_SYMBOL + "=?", new String[]{String.valueOf(symbol)},
                null, null, null);

        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            Kanji kanji = new Kanji();
            kanji.setId(Integer.parseInt(cursor.getString(0)));
            kanji.setSymbol(cursor.getString(1));
            kanji.setStrokeCount(cursor.getInt(2));
            kanji.setGrade(cursor.getInt(3));
            kanji.setJLPT(cursor.getInt(4));
            kanji.setMeanings(cursor.getString(4).split("/"));
            kanji.setKunReadings(cursor.getString(4).split("/"));
            kanji.setOnReadings(cursor.getString(4).split("/"));
            kanji.setUserNote(cursor.getString(4));

            cursor.close();
            db.close();
            return kanji;
        } else {
            db.close();
            return null;
        }
    }

    //Returns a list of all hiragana in the database.
    public List<Hiragana> getAllHiragana() {
        //get all contacts
        List<Hiragana> hiraganaList = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.HIRAGANA_TABLE;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Hiragana hiragana = new Hiragana();

                hiragana.setId(Integer.parseInt(cursor.getString(0)));
                hiragana.setName(cursor.getString(1));
                hiragana.setSymbol(cursor.getString(2));
                hiragana.setColumn(cursor.getString(3));
                hiragana.setUserNote(cursor.getString(4));

                hiraganaList.add(hiragana);
            } while (cursor.moveToNext());
        }
        db.close();
        return hiraganaList;
    }

    //Returns a list of all katakana in the database.
    public List<Katakana> getAllKatakana() {
        //get all contacts
        List<Katakana> katakanaList = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.KATAKANA_TABLE;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Katakana katakana = new Katakana();

                katakana.setId(Integer.parseInt(cursor.getString(0)));
                katakana.setName(cursor.getString(1));
                katakana.setSymbol(cursor.getString(2));
                katakana.setColumn(cursor.getString(3));
                katakana.setUserNote(cursor.getString(4));

                katakanaList.add(katakana);
            } while (cursor.moveToNext());
        }
        db.close();
        return katakanaList;
    }

    //Returns a list of all kanji in the database.
    public List<Kanji> getAllKanji() {
        //get all contacts
        List<Kanji> kanjiList = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.KANJI_TABLE;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Kanji kanji = new Kanji();

                kanji.setId(Integer.parseInt(cursor.getString(0)));
                kanji.setSymbol(cursor.getString(1));
                kanji.setStrokeCount(Integer.parseInt(cursor.getString(2)));
                kanji.setGrade(Integer.parseInt(cursor.getString(3)));
                kanji.setJLPT(Integer.parseInt(cursor.getString(4)));
                kanji.setMeanings(cursor.getString(5).split("/"));
                kanji.setKunReadings(cursor.getString(6).split("/"));
                kanji.setOnReadings(cursor.getString(7).split("/"));
                kanji.setUserNote(cursor.getString(8));

                kanjiList.add(kanji);
            } while (cursor.moveToNext());
        }
        db.close();
        return kanjiList;
    }

    //Updates a hiragana
    public int updateHiragana(Hiragana hiragana) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.HIRAGANA_NAME, hiragana.getName());
        values.put(Util.HIRAGANA_SYMBOL, hiragana.getSymbol());
        values.put(Util.HIRAGANA_COLUMN, hiragana.getColumn());
        values.put(Util.HIRAGANA_USERNOTE, hiragana.getUserNote());

        Log.d("dbdebug", "updateHiragana: " + hiragana.getUserNote());

        int x = db.update(Util.HIRAGANA_TABLE, values, Util.HIRAGANA_ID + "=?", new String[]{String.valueOf(hiragana.getId())});
        db.close();
        return x;
    }

    //Updates a katakana
    public int updateKatakana(Katakana katakana) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KATAKANA_NAME, katakana.getName());
        values.put(Util.KATAKANA_SYMBOL, katakana.getSymbol());
        values.put(Util.KATAKANA_COLUMN, katakana.getColumn());
        values.put(Util.KATAKANA_USERNOTE, katakana.getUserNote());

        int x = db.update(Util.KATAKANA_TABLE, values, Util.KATAKANA_ID + "=?", new String[]{String.valueOf(katakana.getId())});
        db.close();
        return x;
    }

    //Updates a katakana
    public int updateKanji(Kanji kanji) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KANJI_SYMBOL, kanji.getSymbol());
        values.put(Util.KANJI_STROKECOUNT, kanji.getStrokeCount());
        values.put(Util.KANJI_GRADE, kanji.getGrade());
        values.put(Util.KANJI_JLPT, kanji.getJLPT());
        values.put(Util.KANJI_MEANINGS, Util.convertArrayToString(kanji.getMeanings()));
        values.put(Util.KANJI_KUNREADING, Util.convertArrayToString(kanji.getKunReadings()));
        values.put(Util.KANJI_ONREADING, Util.convertArrayToString(kanji.getOnReadings()));
        values.put(Util.KANJI_USERNOTE, kanji.getUserNote());

        int x = db.update(Util.KANJI_TABLE, values, Util.KANJI_SYMBOL + "=?", new String[]{String.valueOf(kanji.getSymbol())});
        db.close();
        return x;
    }

    //Deletes a hiragana
    public void deleteHiragana(Hiragana hiragana) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Util.HIRAGANA_TABLE, Util.HIRAGANA_ID + "=?", new String[]{String.valueOf(hiragana.getId())});
        db.close();
    }

    //Deletes a katakana
    public void deleteKatakana(Katakana katakana) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Util.KATAKANA_TABLE, Util.KATAKANA_ID + "=?", new String[]{String.valueOf(katakana.getId())});
        db.close();
    }

    //Deletes a kanji
    public void deleteKanji(Kanji kanji) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Util.KANJI_TABLE, Util.KANJI_ID + "=?", new String[]{String.valueOf(kanji.getId())});
        db.close();
    }

    //Get number of hiraganas
    public int getHiraganaCount() {
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT * FROM " + Util.HIRAGANA_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    //Get number of katakanas
    public int getKatakanaCount() {
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT * FROM " + Util.KATAKANA_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    //Get number of kanji
    public int getKanjiCount() {
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT * FROM " + Util.KANJI_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}