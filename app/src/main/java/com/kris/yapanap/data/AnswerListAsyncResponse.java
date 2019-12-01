package com.kris.yapanap.data;

import com.kris.yapanap.model.Kanji;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {

    void processFinished(ArrayList<Kanji> kanjiArrayList);
    
}
