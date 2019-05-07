package com.e.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class DisplayWordActivity extends AppCompatActivity {

    private ListView lstWord;
    private Button btnSearch;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);
        btnSearch = findViewById(R.id.btnSearch);
        lstWord = findViewById(R.id.lstWords);
        etSearch = findViewById(R.id.etSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadWord();
            }
        });

    }
    private void LoadWord(){
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
//        wordList = myHelper.GetAllWords(sqLiteDatabase);
        wordList = myHelper.GetWordByName(etSearch.getText().toString(),sqLiteDatabase);

        HashMap<String, String> hashMap = new HashMap<>();
         for (int i =0; i <wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(),wordList.get(i).getMeaning()) ;
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
        );
         lstWord.setAdapter(stringArrayAdapter);


    }

}
