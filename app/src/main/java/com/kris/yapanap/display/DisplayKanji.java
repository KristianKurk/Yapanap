package com.kris.yapanap.display;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kris.yapanap.R;
import com.kris.yapanap.data.DatabaseHandler;
import com.kris.yapanap.model.Kanji;

public class DisplayKanji extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kanji);
        /*
            Display the data in the database.
        */
        Bundle bundle = getIntent().getExtras();
        final String symbol = bundle.getString("symbol");
        DatabaseHandler db = new DatabaseHandler(DisplayKanji.this);
        TextView symbolView = findViewById(R.id.kanjiSymbolView);
        TextView onView = findViewById(R.id.kanjiOnView);
        TextView kunView = findViewById(R.id.kanjiKunView);
        TextView meaningsView = findViewById(R.id.kanjiMeaningsView);
        TextView strokeCountView = findViewById(R.id.kanjiStrokeCountView);
        final EditText userNoteView = findViewById(R.id.userNoteView);
        final Button saveButton = findViewById(R.id.saveNoteButton);
        final ImageButton editBtn = findViewById(R.id.moreImageButton);

        Kanji kanji = db.getKanji(bundle.getString("symbol"));
        symbolView.setText(kanji.getSymbol());
        onView.setText("On Reading:"+bundle.getString("on"));
        kunView.setText("Kun Reading:"+bundle.getString("kun"));
        meaningsView.setText("Meanings: "+bundle.getString("meanings"));
        strokeCountView.setText("Stroke Count: " + kanji.getStrokeCount());

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}

