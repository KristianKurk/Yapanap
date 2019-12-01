package com.kris.yapanap.data;

import android.app.Instrumentation;

import org.junit.Test;

import androidx.test.InstrumentationRegistry;

import com.kris.yapanap.model.Hiragana;
import com.kris.yapanap.model.Kanji;
import com.kris.yapanap.model.Katakana;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseHandlerTest {

    /*
    I thought we had to code unit tests too, until I read the instructions properly.
    I kept them anyways, because why not.
     */

    DatabaseHandler db = new DatabaseHandler(InstrumentationRegistry.getInstrumentation().getContext());

    @Test
    public void getHiragana() {
        //Arrange
        String expected = "あ";
        //Act
        String result = db.getHiragana(0).getSymbol();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    public void getAllKanji() {
        //Arrange

        //Act
        List<Kanji> list = db.getAllKanji();

        //Assert
        assertNotEquals(null,list);
    }

    @Test
    public void updateKatakana() {
        //Arrange
        Katakana katakana = db.getKatakana(0);
        String expected = "test user note";

        //Act
        katakana.setUserNote(expected);
        db.updateKatakana(katakana);

        //Assert
        assertEquals(db.getKatakana(0).getUserNote(),expected);
    }
}