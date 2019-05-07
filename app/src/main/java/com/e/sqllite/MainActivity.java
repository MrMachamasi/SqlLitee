package com.e.sqllite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helper.MyHelper;

public class MainActivity extends AppCompatActivity {

    EditText etWord, etMeaning;
    Button btnAddWord,btnShowWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAddWord = findViewById(R.id.btnAddWord);
        btnShowWords = findViewById(R.id.btnShowWords);

        final MyHelper myHelper = new MyHelper( this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

     btnAddWord.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            long id = myHelper.InsertData(etWord.getText().toString(),etMeaning.getText().toString(),sqLiteDatabase);
            if(id> 0){
                Toast.makeText(MainActivity.this, "Successfull" + id, Toast.LENGTH_SHORT ).show();
            }else{
                Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
            }
         }
     });
     btnShowWords.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this,DisplayWordActivity.class);
             startActivity(intent);
         }
     });

        }
    }

