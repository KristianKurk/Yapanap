package com.kris.yapanap.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    /*
    This page checks to see if a fragment has been run for the first time.
    I use this so that the app data needs to be initialised only once.
     */

    public Prefs(Activity activity){
        this.preferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public boolean isFirstTimeRunHiragana(){
        if (preferences.getBoolean("hiragana", true)) {
            preferences.edit().putBoolean("hiragana",false).apply();
            return true;
        } else {
            return false;
        }
    }

    public boolean isFirstTimeRunKatakana(){
        if (preferences.getBoolean("katakana", true)) {
            preferences.edit().putBoolean("katakana",false).apply();
            return true;
        } else {
            return false;
        }
    }

    public boolean isFirstTimeRunKanji(){
        if (preferences.getBoolean("kanji", true)) {
            preferences.edit().putBoolean("kanji",false).apply();
            return true;
        } else {
            return false;
        }
    }

}
