package com.kris.yapanap.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kris.yapanap.R;
import com.kris.yapanap.adapter.KanjiAdapter;
import com.kris.yapanap.data.AnswerKanjiAsyncResponse;
import com.kris.yapanap.data.AnswerListAsyncResponse;
import com.kris.yapanap.data.DataLoader;
import com.kris.yapanap.data.DatabaseHandler;
import com.kris.yapanap.model.Kanji;
import com.kris.yapanap.util.Prefs;
import com.kris.yapanap.util.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class KanjiFragment extends Fragment {

    private RecyclerView recyclerView;
    private KanjiAdapter kanjiAdapter;
    private List<Kanji> kanjiList;
    private DatabaseHandler db;
    private View view;

    public KanjiFragment() {
        //Required empty constructor.
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
        This portion deals only with the recycler view.
         */

        view = inflater.inflate(R.layout.fragment_kanji, container, false);
        db = new DatabaseHandler(view.getContext());

        recyclerView = view.findViewById(R.id.kanjirecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        /*
        kanjiList = db.getAllKanji();
        for (Kanji k : kanjiList)
            db.deleteKanji(k);*/

        Prefs prefs = new Prefs(getActivity());

        if (prefs.isFirstTimeRunKanji()) {
            init();
        } else {
            kanjiList = db.getAllKanji();
            kanjiAdapter = new KanjiAdapter(view.getContext(), kanjiList);
            recyclerView.setAdapter(kanjiAdapter);
        }


        return view;
    }


    private void finish() {
        for (Kanji k : kanjiList)
            db.updateKanji(k);

        kanjiAdapter = new KanjiAdapter(view.getContext(), kanjiList);
        recyclerView.setAdapter(kanjiAdapter);

        db.close();
    }


    //Initialises the data in the kanji database.
    public void init(){
        kanjiList = new DataLoader().initKanji(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Kanji> kanjiArrayList) {
                for (Kanji k : kanjiList)
                    db.addKanji(k);

                Log.d("SIZE", "array before: " + kanjiArrayList.size());
                Log.d("SIZE", "list before: " + kanjiList.size());

                int size = kanjiList.size();
                for (int i = 0; i < size; i++) {
                    String url2 = "https://kanjiapi.dev/v1/kanji/" + kanjiList.get(i).getSymbol();

                    final int finalI = i;
                    Kanji k = new DataLoader().loadKanji(new AnswerKanjiAsyncResponse() {
                        @Override
                        public void processFinished(Kanji kanji) {
                            kanjiList.get(finalI).setMeanings(kanji.getMeanings());
                kanjiList.get(finalI).setStrokeCount(kanji.getStrokeCount());
                kanjiList.get(finalI).setJLPT(kanji.getJLPT());
                kanjiList.get(finalI).setGrade(kanji.getGrade());
                kanjiList.get(finalI).setKunReadings(kanji.getKunReadings());
                kanjiList.get(finalI).setOnReadings(kanji.getOnReadings());

                if (finalI == kanjiList.size() - 1)
                    finish();
            }
        }, url2);
    }
            }
        });
    }
}