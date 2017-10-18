package com.example.lenovo.wordbook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class CheckWord extends AppCompatActivity {

    private String condition;
    private EditText editText;
    private Cursor cursor;
    private  WordDataSQL wordDataSQL;
    private  List<Word> mword= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkword);
        wordDataSQL = new WordDataSQL(this, "WordBook.db", null, 1);
        final RecyclerView wordTitleRecyclerView = (RecyclerView) findViewById(R.id.searchword_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        wordTitleRecyclerView.setLayoutManager(layoutManager);
        editText= (EditText) findViewById(R.id.search_word_input_edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                condition=editText.getText().toString();
                cursor = wordDataSQL.getReadableDatabase()
                        .rawQuery("select * from Word where word like '"
                                +condition+"%' or content like '"+condition+"%'",null);
                changeCursor(cursor);
                AdapterCHECK adapterCHECK =new AdapterCHECK(mword);
                wordTitleRecyclerView.setAdapter(adapterCHECK);
            }
        });
    }
    public void changeCursor(Cursor cursor) {
        mword.clear();
        if(cursor.moveToFirst()&&cursor!=null){
            do{
                Word word=new Word(null,null);
                word.setWord(cursor.getString(cursor.getColumnIndex("word")));
                word.setContent(cursor.getString(cursor.getColumnIndex("content")));
                mword.add(word);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
