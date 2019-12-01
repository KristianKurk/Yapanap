package com.kris.yapanap.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kris.yapanap.display.DisplayKana;
import com.kris.yapanap.R;
import com.kris.yapanap.model.*;
import com.kris.yapanap.util.Util;

import java.util.List;

public class HiraganaAdapter extends RecyclerView.Adapter<HiraganaAdapter.ViewHolder> {
    private Context context;
    private List<Hiragana> hiraganaList;
    private List<String> rowList;

    public HiraganaAdapter(Context context, List<Hiragana> hiraganaList){
        this.context = context;
        this.hiraganaList = hiraganaList;
        rowList = Util.getRowList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kana_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String row = rowList.get(position);
        //Set the on click for the buttons.

        for (int i = 0; i < 5; i ++) {
            Button b = holder.hiraganaViews[i];
            Hiragana hiragana = hiraganaList.get(position*5+i);
            b.setText(hiragana.getSymbol());
            final int finalposition = position*5+i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DisplayKana.class);

                    Bundle bundle = new Bundle();
                    Hiragana hiragana = hiraganaList.get(finalposition);
                    bundle.putString("type", "hiragana");
                    bundle.putInt("id",hiragana.getId());;

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return rowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Get the buttons.
        Button[] hiraganaViews = {
                itemView.findViewById(R.id.button1),
                itemView.findViewById(R.id.button2),
                itemView.findViewById(R.id.button3),
                itemView.findViewById(R.id.button4),
                itemView.findViewById(R.id.button5),
        };


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
