package com.kris.yapanap.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kris.yapanap.controller.AppController;
import com.kris.yapanap.model.Hiragana;
import com.kris.yapanap.model.Kanji;
import com.kris.yapanap.model.Katakana;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private ArrayList<Kanji> kanjiArrayList = new ArrayList<Kanji>();
    private String url1 = "https://kanjiapi.dev/v1/kanji/all";
    private Kanji kanji = new Kanji();


    //Get a list of kanji symbols.
    public List<Kanji> initKanji(final AnswerListAsyncResponse callback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url1,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("jsondata", "response: we got a response!" + response.length());
                            for (int i = 0; i < 100; i++) {
                                Kanji kanji = new Kanji();
                                kanji.setSymbol(response.getString(i));
                                kanjiArrayList.add(i, kanji);
                                Log.d("jsondata", "response: inside loop");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (null != callback) {
                            Log.d("jsondata", "response: it worked?");
                            callback.processFinished(kanjiArrayList);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("jsondata", "response: error" + error.getMessage());
                error.printStackTrace();
            }
        }
        );
        Log.d("jsondata", "loadKanji: " + jsonArrayRequest);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return kanjiArrayList;
    }

    //Fetch the data for a specific kanji symbol.
    public Kanji loadKanji(final AnswerKanjiAsyncResponse callback, String url2) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Log.d("jsondata", "second response"+kanji.getSymbol() );
                            //kanji.setGrade(response.getInt("grade"));
                            //kanji.setJLPT(response.getInt("jlpt"));
                            kanji.setStrokeCount(response.getInt("stroke_count"));
                            kanji.setSymbol(response.getString("kanji"));

                            JSONArray onReadings = response.getJSONArray("on_readings");
                            JSONArray kunreadings = response.getJSONArray("kun_readings");
                            JSONArray meanings = response.getJSONArray("meanings");

                            String[] onList = new String[onReadings.length()];
                            String[] kunList = new String[kunreadings.length()];
                            String[] meaningsList = new String[meanings.length()];

                            for (int j = 0; j < onReadings.length(); j++)
                                onList[j] = onReadings.getString(j);
                            for (int j = 0; j < kunreadings.length(); j++)
                                kunList[j] = kunreadings.getString(j);
                            for (int j = 0; j < meanings.length(); j++)
                                meaningsList[j] = meanings.getString(j);


                            kanji.setMeanings(meaningsList);
                            kanji.setKunReadings(kunList);
                            kanji.setOnReadings(onList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (null != callback) {
                            callback.processFinished(kanji);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("jsondata", "SECOND ERROR: error" + error.getMessage());
                error.printStackTrace();
            }
        }
        );
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        return kanji;
    }

    //Add all katakana.
    public static void addAllKatakana(DatabaseHandler db) {
        db.addKatakana(new Katakana("a", "ア", "a", ""));
        db.addKatakana(new Katakana("i", "イ", "a", ""));
        db.addKatakana(new Katakana("u", "ウ", "a", ""));
        db.addKatakana(new Katakana("e", "エ", "a", ""));
        db.addKatakana(new Katakana("o", "オ", "a", ""));
        db.addKatakana(new Katakana("ka", "カ", "k", ""));
        db.addKatakana(new Katakana("ki", "キ", "k", ""));
        db.addKatakana(new Katakana("ku", "ク", "k", ""));
        db.addKatakana(new Katakana("ke", "ケ", "k", ""));
        db.addKatakana(new Katakana("ko", "コ", "k", ""));
        db.addKatakana(new Katakana("ga", "ガ", "g", ""));
        db.addKatakana(new Katakana("gi", "ギ", "g", ""));
        db.addKatakana(new Katakana("gu", "グ", "g", ""));
        db.addKatakana(new Katakana("ge", "ゲ", "g", ""));
        db.addKatakana(new Katakana("go", "ゴ", "g", ""));
        db.addKatakana(new Katakana("sa", "サ", "s", ""));
        db.addKatakana(new Katakana("shi", "シ", "s", ""));
        db.addKatakana(new Katakana("su", "ス", "s", ""));
        db.addKatakana(new Katakana("se", "セ", "s", ""));
        db.addKatakana(new Katakana("so", "ソ", "s", ""));
        db.addKatakana(new Katakana("za", "ザ", "z", ""));
        db.addKatakana(new Katakana("ji", "ジ", "z", ""));
        db.addKatakana(new Katakana("zu", "ズ", "z", ""));
        db.addKatakana(new Katakana("ze", "ゼ", "z", ""));
        db.addKatakana(new Katakana("zo", "ゾ", "z", ""));
        db.addKatakana(new Katakana("ta", "タ", "t", ""));
        db.addKatakana(new Katakana("chi", "チ", "t", ""));
        db.addKatakana(new Katakana("tsu", "ツ", "t", ""));
        db.addKatakana(new Katakana("te", "テ", "t", ""));
        db.addKatakana(new Katakana("to", "ト", "t", ""));
        db.addKatakana(new Katakana("da", "ダ", "d", ""));
        db.addKatakana(new Katakana("dji", "ヂ", "d", ""));
        db.addKatakana(new Katakana("dzu", "ヅ", "d", ""));
        db.addKatakana(new Katakana("de", "デ", "d", ""));
        db.addKatakana(new Katakana("do", "ド", "d", ""));
        db.addKatakana(new Katakana("na", "ナ", "n", ""));
        db.addKatakana(new Katakana("ni", "ニ", "n", ""));
        db.addKatakana(new Katakana("nu", "ヌ", "n", ""));
        db.addKatakana(new Katakana("ne", "ネ", "n", ""));
        db.addKatakana(new Katakana("no", "ノ", "n", ""));
        db.addKatakana(new Katakana("ha", "ハ", "h", ""));
        db.addKatakana(new Katakana("hi", "ヒ", "h", ""));
        db.addKatakana(new Katakana("fu", "フ", "h", ""));
        db.addKatakana(new Katakana("he", "ヘ", "h", ""));
        db.addKatakana(new Katakana("ho", "ホ", "h", ""));
        db.addKatakana(new Katakana("ba", "バ", "b", ""));
        db.addKatakana(new Katakana("bi", "ビ", "b", ""));
        db.addKatakana(new Katakana("bu", "ブ", "b", ""));
        db.addKatakana(new Katakana("be", "ベ", "b", ""));
        db.addKatakana(new Katakana("bo", "ボ", "b", ""));
        db.addKatakana(new Katakana("pa", "パ", "p", ""));
        db.addKatakana(new Katakana("pi", "ピ", "p", ""));
        db.addKatakana(new Katakana("pu", "プ", "p", ""));
        db.addKatakana(new Katakana("pe", "ペ", "p", ""));
        db.addKatakana(new Katakana("po", "ポ", "p", ""));
        db.addKatakana(new Katakana("ma", "マ", "m", ""));
        db.addKatakana(new Katakana("mi", "ミ", "m", ""));
        db.addKatakana(new Katakana("mu", "ム", "m", ""));
        db.addKatakana(new Katakana("me", "メ", "m", ""));
        db.addKatakana(new Katakana("mo", "モ", "m", ""));
        db.addKatakana(new Katakana("ya", "ヤ", "y", ""));
        db.addKatakana(new Katakana("", "", "y", ""));
        db.addKatakana(new Katakana("yu", "ユ", "y", ""));
        db.addKatakana(new Katakana("", "", "y", ""));
        db.addKatakana(new Katakana("yo", "ヨ", "y", ""));
        db.addKatakana(new Katakana("ra", "ラ", "r", ""));
        db.addKatakana(new Katakana("ri", "リ", "r", ""));
        db.addKatakana(new Katakana("ru", "ル", "r", ""));
        db.addKatakana(new Katakana("re", "レ", "r", ""));
        db.addKatakana(new Katakana("ro", "ロ", "r", ""));
        db.addKatakana(new Katakana("wa", "ワ", "w", ""));
        db.addKatakana(new Katakana("", "", "w", ""));
        db.addKatakana(new Katakana("", "", "w", ""));
        db.addKatakana(new Katakana("", "", "w", ""));
        db.addKatakana(new Katakana("wo", "ヲ", "w", ""));
    }

    //Add all hiragana.
    public static void addAllHiragana(DatabaseHandler db) {
        db.addHiragana(new Hiragana("a", "あ", "a", ""));
        db.addHiragana(new Hiragana("i", "い", "a", ""));
        db.addHiragana(new Hiragana("u", "う", "a", ""));
        db.addHiragana(new Hiragana("e", "え", "a", ""));
        db.addHiragana(new Hiragana("o", "お", "a", ""));
        db.addHiragana(new Hiragana("ka", "か", "k", ""));
        db.addHiragana(new Hiragana("ki", "き", "k", ""));
        db.addHiragana(new Hiragana("ku", "く", "k", ""));
        db.addHiragana(new Hiragana("ke", "け", "k", ""));
        db.addHiragana(new Hiragana("ko", "こ", "k", ""));
        db.addHiragana(new Hiragana("ga", "が", "g", ""));
        db.addHiragana(new Hiragana("gi", "ぎ", "g", ""));
        db.addHiragana(new Hiragana("gu", "ぐ", "g", ""));
        db.addHiragana(new Hiragana("ge", "げ", "g", ""));
        db.addHiragana(new Hiragana("go", "ご", "g", ""));
        db.addHiragana(new Hiragana("sa", "さ", "s", ""));
        db.addHiragana(new Hiragana("shi", "し", "s", ""));
        db.addHiragana(new Hiragana("su", "す", "s", ""));
        db.addHiragana(new Hiragana("se", "せ", "s", ""));
        db.addHiragana(new Hiragana("so", "そ", "s", ""));
        db.addHiragana(new Hiragana("za", "ざ", "z", ""));
        db.addHiragana(new Hiragana("ji", "じ", "z", ""));
        db.addHiragana(new Hiragana("zu", "ず", "z", ""));
        db.addHiragana(new Hiragana("ze", "ぜ", "z", ""));
        db.addHiragana(new Hiragana("zo", "ぞ", "z", ""));
        db.addHiragana(new Hiragana("ta", "た", "t", ""));
        db.addHiragana(new Hiragana("chi", "ち", "t", ""));
        db.addHiragana(new Hiragana("tsu", "つ", "t", ""));
        db.addHiragana(new Hiragana("te", "て", "t", ""));
        db.addHiragana(new Hiragana("to", "と", "t", ""));
        db.addHiragana(new Hiragana("da", "だ", "d", ""));
        db.addHiragana(new Hiragana("dji", "ぢ", "d", ""));
        db.addHiragana(new Hiragana("dzu", "づ", "d", ""));
        db.addHiragana(new Hiragana("de", "で", "d", ""));
        db.addHiragana(new Hiragana("do", "ど", "d", ""));
        db.addHiragana(new Hiragana("na", "な", "n", ""));
        db.addHiragana(new Hiragana("ni", "に", "n", ""));
        db.addHiragana(new Hiragana("nu", "ぬ", "n", ""));
        db.addHiragana(new Hiragana("ne", "ね", "n", ""));
        db.addHiragana(new Hiragana("no", "の", "n", ""));
        db.addHiragana(new Hiragana("ha", "は", "h", ""));
        db.addHiragana(new Hiragana("hi", "ひ", "h", ""));
        db.addHiragana(new Hiragana("fu", "ふ", "h", ""));
        db.addHiragana(new Hiragana("he", "へ", "h", ""));
        db.addHiragana(new Hiragana("ho", "ほ", "h", ""));
        db.addHiragana(new Hiragana("ba", "ば", "b", ""));
        db.addHiragana(new Hiragana("bi", "び", "b", ""));
        db.addHiragana(new Hiragana("bu", "ぶ", "b", ""));
        db.addHiragana(new Hiragana("be", "べ", "b", ""));
        db.addHiragana(new Hiragana("bo", "ぼ", "b", ""));
        db.addHiragana(new Hiragana("pa", "ぱ", "p", ""));
        db.addHiragana(new Hiragana("pi", "ぴ", "p", ""));
        db.addHiragana(new Hiragana("pu", "ぷ", "p", ""));
        db.addHiragana(new Hiragana("pe", "ぺ", "p", ""));
        db.addHiragana(new Hiragana("po", "ぽ", "p", ""));
        db.addHiragana(new Hiragana("ma", "ま", "m", ""));
        db.addHiragana(new Hiragana("mi", "み", "m", ""));
        db.addHiragana(new Hiragana("mu", "む", "m", ""));
        db.addHiragana(new Hiragana("me", "め", "m", ""));
        db.addHiragana(new Hiragana("mo", "も", "m", ""));
        db.addHiragana(new Hiragana("ya", "や", "y", ""));
        db.addHiragana(new Hiragana("", "", "y", ""));
        db.addHiragana(new Hiragana("yu", "ゆ", "y", ""));
        db.addHiragana(new Hiragana("", "", "y", ""));
        db.addHiragana(new Hiragana("yo", "よ", "y", ""));
        db.addHiragana(new Hiragana("ra", "ら", "r", ""));
        db.addHiragana(new Hiragana("ri", "り", "r", ""));
        db.addHiragana(new Hiragana("ru", "る", "r", ""));
        db.addHiragana(new Hiragana("re", "れ", "r", ""));
        db.addHiragana(new Hiragana("ro", "ろ", "r", ""));
        db.addHiragana(new Hiragana("wa", "わ", "w", ""));
        db.addHiragana(new Hiragana("", "", "w", ""));
        db.addHiragana(new Hiragana("", "", "w", ""));
        db.addHiragana(new Hiragana("", "", "w", ""));
        db.addHiragana(new Hiragana("wo", "を", "w", ""));
    }

}
