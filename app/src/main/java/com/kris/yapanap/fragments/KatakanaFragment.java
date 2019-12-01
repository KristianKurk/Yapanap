package com.kris.yapanap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kris.yapanap.MainActivity;
import com.kris.yapanap.R;
import com.kris.yapanap.adapter.HiraganaAdapter;
import com.kris.yapanap.adapter.KatakanaAdapter;
import com.kris.yapanap.data.DataLoader;
import com.kris.yapanap.data.DatabaseHandler;
import com.kris.yapanap.model.Hiragana;
import com.kris.yapanap.model.Katakana;
import com.kris.yapanap.util.Prefs;
import com.kris.yapanap.util.Util;

import java.util.List;

public class KatakanaFragment extends Fragment {

    private RecyclerView recyclerView;
    private KatakanaAdapter katakanaAdapter;
    DatabaseHandler db;
    List<Katakana> katakanaList;

    public KatakanaFragment(){
        //Required empty constructor.
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        /*
        This portion deals only with the recycler view.
         */
        View view = inflater.inflate(R.layout.fragment_katakana,container,false);
        db = new DatabaseHandler(view.getContext());

        recyclerView = view.findViewById(R.id.katakanarecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        /*
        katakanaList =  db.getAllKatakana();
        for (Katakana k : katakanaList)
            db.deleteKatakana(k);*/

        Prefs prefs = new Prefs(getActivity());

        if (prefs.isFirstTimeRunKatakana())
            init();

        katakanaList =  db.getAllKatakana();

        katakanaAdapter = new KatakanaAdapter(view.getContext(),katakanaList);
        recyclerView.setAdapter(katakanaAdapter);

        db.close();

        return view;
    }

    private void init(){
        DataLoader.addAllKatakana(db);
    }
}