package com.kris.yapanap.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kris.yapanap.display.DisplayKanji;
import com.kris.yapanap.R;
import com.kris.yapanap.model.Kanji;

import java.util.List;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.ViewHolder> {
    private Context context;
    private List<Kanji> kanjiList;

    public KanjiAdapter(Context context, List<Kanji> kanjiList){
        this.context = context;
        this.kanjiList = kanjiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kanji_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kanji kanji = kanjiList.get(position);

        holder.kanjiSymbolview.setText(kanji.getSymbol());
        holder.kanjiNameView.setText(kanji.getMeanings()[0]);
    }


    @Override
    public int getItemCount() {
        return kanjiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView kanjiSymbolview;
        private TextView kanjiNameView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kanjiNameView = itemView.findViewById(R.id.kanjiNameView);
            kanjiSymbolview = itemView.findViewById(R.id.kanjiSymbolView);

            //On click for each item.
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DisplayKanji.class);

                        Bundle bundle = new Bundle();
                        Kanji kanji = kanjiList.get(getAdapterPosition());
                        bundle.putString("type", "kanji");
                        bundle.putString("symbol", kanji.getSymbol());
                        bundle.putString("meanings", kanji.getMeaningsText());
                        bundle.putString("kun", kanji.getKunReadingsText());
                        bundle.putString("on", kanji.getOnReadingsText());
                        Log.d("HELPME", "onClick: "+kanji.getSymbol());

                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });
        }
    }
}
