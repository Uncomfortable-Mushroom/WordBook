package com.example.lenovo.wordbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WordContentActivity extends AppCompatActivity {
    private Button delete,add;
    private WordDataSQL wordDataSQL;

    public static void actionStart(Context context, String wordTitle, String wordContent) {
        Intent intent = new Intent(context, WordContentActivity.class);
        intent.putExtra("word_title", wordTitle);
        intent.putExtra("word_content", wordContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_content);
        final String wordTitle = getIntent().getStringExtra("word_title"); // 获取传入的单词标题
        final String wordContent = getIntent().getStringExtra("word_content"); // 获取传入的单词内容
        WordContentFragment wordContentFragment = (WordContentFragment) getSupportFragmentManager().findFragmentById(R.id.word_content_fragment);
        wordContentFragment.refresh(wordTitle, wordContent); // 刷新WordContentFragment界面

        wordDataSQL = new WordDataSQL(this, "WordBook.db", null, 1);
        delete = (Button) findViewById(R.id.delete_word);
        add = (Button) findViewById(R.id.add_new_word);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Word word = new Word(wordTitle, wordContent);
                    wordDataSQL.delete(wordDataSQL, word, "Word");
                    wordDataSQL.delete(wordDataSQL, word, "NewWord");
                    refresh();
                    Toast.makeText(getApplicationContext(),
                            "删除成功", Toast.LENGTH_LONG).show();
                }
            });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word(wordTitle,wordContent);
                wordDataSQL.insert(wordDataSQL, word, "NewWord");
                Toast.makeText(getApplicationContext(),
                        "收藏成功", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void refresh(){
        finish();
        Intent intent1=new Intent(WordContentActivity.this,MainActivity.class);
        startActivity(intent1);
    }

}
