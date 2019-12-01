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
import com.kris.yapanap.model.Hiragana;
import com.kris.yapanap.model.Katakana;

public class DisplayKana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kana);
        /*
            Display the data in the database.
        */
        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");
        DatabaseHandler db = new DatabaseHandler(DisplayKana.this);
        TextView symbolView = findViewById(R.id.kanaSymbolView);
        TextView nameView = findViewById(R.id.kanaNameView);
        TextView columnView = findViewById(R.id.kanaColumnView);
        final EditText userNoteView = findViewById(R.id.userNoteView);
        final Button saveButton = findViewById(R.id.saveNoteButton);
        final ImageButton editBtn = findViewById(R.id.moreImageButton);

        if (bundle.getString("type").equals("hiragana")) {
            Hiragana hiragana = db.getHiragana(bundle.getInt("id"));
            symbolView.setText(hiragana.getSymbol());
            nameView.setText("Name: " + hiragana.getName());
            columnView.setText("Column: " + hiragana.getColumn());


            String note = hiragana.getUserNote();

            if (note != null && !note.isEmpty())
                userNoteView.setText(note);
            else
                userNoteView.setHint("Press button to edit note.");

            userNoteView.setEnabled(false);

            setTitle("Hiragana: " + hiragana.getName());

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHandler db = new DatabaseHandler(DisplayKana.this);
                    Hiragana hiragana = db.getHiragana(id);
                    hiragana.setUserNote(userNoteView.getText().toString());
                    int x = db.updateHiragana(hiragana);
                    editBtn.setVisibility(View.VISIBLE);
                    saveButton.setVisibility(View.GONE);
                    userNoteView.setEnabled(true);
                }
            });
        } else if(bundle.getString("type").equals("katakana")){
            Katakana katakana = db.getKatakana(bundle.getInt("id"));
            symbolView.setText(katakana.getSymbol());
            nameView.setText("Name: " + katakana.getName());
            columnView.setText("Column: " + katakana.getColumn());


            String note = katakana.getUserNote();

            if (note != null && !note.isEmpty())
                userNoteView.setText(note);
            else
                userNoteView.setHint("Press button to edit note.");

            userNoteView.setEnabled(false);

            setTitle("Katakana: " + katakana.getName());

            //Save button on click.
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHandler db = new DatabaseHandler(DisplayKana.this);
                    Katakana katakana = db.getKatakana(id);
                    katakana.setUserNote(userNoteView.getText().toString());
                    int x = db.updateKatakana(katakana);
                    editBtn.setVisibility(View.VISIBLE);
                    saveButton.setVisibility(View.GONE);
                    userNoteView.setEnabled(true);
                }
            });
        }

        //Back button on click
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });


        //Edit button on click
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNoteView.setEnabled(true);
                userNoteView.setHint("Enter note.");
                editBtn.setVisibility(View.GONE);
                saveButton.setVisibility(View.VISIBLE);
            }
        });

    }
}
