package com.example.lenovo.wordbook;

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
import java.util.Random;

public class Begin extends AppCompatActivity {

    private  WordDataSQL wordDataSQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin);
        RecyclerView wordTitleRecyclerView = (RecyclerView) findViewById(R.id.word_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        wordTitleRecyclerView.setLayoutManager(layoutManager);
        WordTitleFragment wordTitleFragment=(WordTitleFragment)getSupportFragmentManager().findFragmentById(R.id.word_title_fragment);

         wordDataSQL=new  WordDataSQL(this,"WordBook.db",null,1);
         wordDataSQL.clear(wordDataSQL);
        for(Word w:getWord()){
            wordDataSQL.insert(wordDataSQL,w);
        }

        WordTitleFragment.WordAdapter adapter =
                wordTitleFragment.new WordAdapter(wordDataSQL.getall(wordDataSQL));
        wordTitleRecyclerView.setAdapter(adapter);
    }
    private List<Word> getWord() {
        List<Word> wordList = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        String line="";
        BufferedReader reader=null;
        InputStreamReader inputStreamReader=null;
        InputStream inputStream = getResources().openRawResource(R.raw.a);
        try{
            inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            reader=new BufferedReader(inputStreamReader);
            while ((line=reader.readLine())!=null){
                    s.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        for (int i = 0; i <s.size(); i++) {
            Word word = new Word();
           word.setWord(s.get(i));
           word.setContent(s.get(i+1));
           wordList.add(word);
            i++;
        }
        return wordList;
    }


    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }
}
