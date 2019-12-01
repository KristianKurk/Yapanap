package com.kris.yapanap.util;

import com.kris.yapanap.data.DatabaseHandler;
import com.kris.yapanap.model.Hiragana;
import com.kris.yapanap.model.Katakana;

import java.util.Arrays;
import java.util.List;

public class Util {
    //DB Info
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "yapanap.db";

    //Table Info
    public static final String HIRAGANA_TABLE = "hiragana";
    public static final String KATAKANA_TABLE = "katakana";
    public static final String KANJI_TABLE = "kanji";

    //Hiragana Columns
    public static final String HIRAGANA_ID = "hiragana_id";
    public static final String HIRAGANA_NAME = "hiragana_name";
    public static final String HIRAGANA_SYMBOL = "hiragana_symbol";
    public static final String HIRAGANA_COLUMN = "hiragana_column";
    public static final String HIRAGANA_USERNOTE = "hiragana_user_note";

    //Katakana Columns
    public static final String KATAKANA_ID = "katakana_id";
    public static final String KATAKANA_NAME = "katakana_name";
    public static final String KATAKANA_SYMBOL = "katakana_symbol";
    public static final String KATAKANA_COLUMN = "katakana_column";
    public static final String KATAKANA_USERNOTE = "katakana_user_note";

    //Kanji Columns
    public static final String KANJI_ID = "kanji_id";
    public static final String KANJI_SYMBOL = "kanji_symbol";
    public static final String KANJI_GRADE = "kanji_grade";
    public static final String KANJI_JLPT = "kanji_jlpt";
    public static final String KANJI_STROKECOUNT = "kanji_stroke_count";
    public static final String KANJI_MEANINGS = "kanji_meanings";
    public static final String KANJI_KUNREADING = "kanji_kun_reading";
    public static final String KANJI_ONREADING = "kanji_on_reading";
    public static final String KANJI_USERNOTE = "kanji_user_note";

    public static String convertArrayToString(String[] strArray) {
        String joinedString = String.join("/", strArray);
        return joinedString;
    }

    public static List<String> getRowList(){
        return Arrays.asList( "a","k","g","s","z","t","d","n","h","b","p","m","y","r","w");
    }

}
