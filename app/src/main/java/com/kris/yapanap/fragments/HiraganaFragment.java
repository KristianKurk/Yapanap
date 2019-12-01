package com.kris.yapanap.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kris.yapanap.R;
import com.kris.yapanap.adapter.HiraganaAdapter;
import com.kris.yapanap.data.DataLoader;
import com.kris.yapanap.data.DatabaseHandler;
import com.kris.yapanap.model.Hiragana;
import com.kris.yapanap.util.Prefs;
import com.kris.yapanap.util.Util;

import java.util.List;

public class HiraganaFragment extends Fragment {

    private RecyclerView recyclerView;
    private HiraganaAdapter hiraganaAdapter;
    private DatabaseHandler db;
    List<Hiragana> hiraganaList;

    public HiraganaFragment(){
        //Required empty constructor.
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        /*
        This portion deals only with the recycler view.
         */

        View view = inflater.inflate(R.layout.fragment_hiragana,container,false);
        db = new DatabaseHandler(view.getContext());

        recyclerView = view.findViewById(R.id.hiraganarecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        /*
        List<Hiragana> hiraganaList =  db.getAllHiragana();
        for (Hiragana h : hiraganaList)
            db.deleteHiragana(h);*/

        Prefs prefs = new Prefs(getActivity());
        if (prefs.isFirstTimeRunHiragana())
            init();

        hiraganaList =  db.getAllHiragana();

        hiraganaAdapter = new HiraganaAdapter(view.getContext(),hiraganaList);
        recyclerView.setAdapter(hiraganaAdapter);

        db.close();

        return view;
    }

    private void init(){
        DataLoader.addAllHiragana(db);
    }
}