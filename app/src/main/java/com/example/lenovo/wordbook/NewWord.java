package com.example.lenovo.wordbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class NewWord extends AppCompatActivity {

    private  WordDataSQL wordDataSQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newword);
        RecyclerView wordTitleRecyclerView = (RecyclerView) findViewById(R.id.newword_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        wordTitleRecyclerView.setLayoutManager(layoutManager);
        wordDataSQL = new WordDataSQL(this, "WordBook.db", null, 1);
        AdapterNEWWORD adapter =new AdapterNEWWORD(wordDataSQL.getall(wordDataSQL, "NewWord"));
        wordTitleRecyclerView.setAdapter(adapter);
    }
}
