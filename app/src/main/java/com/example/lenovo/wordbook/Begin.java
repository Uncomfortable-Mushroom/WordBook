package com.example.lenovo.wordbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Begin extends AppCompatActivity {

    private WordDataSQL wordDataSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin);



        wordDataSQL = new WordDataSQL(this, "WordBook.db", null, 1);
        //数据初始化
        if(wordDataSQL.isEmpty(wordDataSQL,"Word"))
          for (Word w : getWord()) {
                wordDataSQL.insert(wordDataSQL, w, "Word");
            }
        RecyclerView wordTitleRecyclerView = (RecyclerView) findViewById(R.id.word_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        wordTitleRecyclerView.setLayoutManager(layoutManager);
        WordTitleFragment wordTitleFragment = (WordTitleFragment) getSupportFragmentManager().findFragmentById(R.id.word_title_fragment);

        WordTitleFragment.WordAdapter adapter =
                wordTitleFragment.new WordAdapter(wordDataSQL.getall(wordDataSQL, "Word"));
        wordTitleRecyclerView.setAdapter(adapter);
    }
    private List<Word> getWord() {
        List<Word> wordList = new ArrayList<>();
        String string="";
        boolean i=true;
        String line = "";
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = getResources().openRawResource(R.raw.a);
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            reader = new BufferedReader(inputStreamReader);
            while ((line = reader.readLine()) != null) {
                if(i){
                    string=line;
                    i=false;
                }else{
                    wordList.add(new Word(string,line));
                    i=true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }
}
