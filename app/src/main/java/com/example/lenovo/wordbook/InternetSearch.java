package com.example.lenovo.wordbook;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class InternetSearch  extends AppCompatActivity{

    private WordDataSQL wordDataSQL;
    private EditText content;
    private TextView Word,Phonetic,Trans;
    private Button search,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_search);

        wordDataSQL = new WordDataSQL(this, "WordBook.db", null, 1);
        content=(EditText)findViewById(R.id.search_wordORcontent);
        Word=(TextView)findViewById(R.id.word);
        Phonetic=(TextView)findViewById(R.id.phonetic);
        Trans=(TextView)findViewById(R.id.trans);
        add=(Button)findViewById(R.id.add);
        search=(Button)findViewById(R.id.search);

        Intent intent=getIntent();
        String string=intent.getStringExtra("extra");

        if(string!=null&&string.length()>0){
            content.setText(string);
        }

        class WordTask extends AsyncTask {
            private String url = "http://dict-co.iciba.com/api/dictionary.php?w=";
            private String url1 = "&key=FDE3D3CDEF598D851D3CF6EAA96AC278";
            private Document doc;
            private String word;
            private String phonetic ;
            private String trans ;
            public WordTask(){
                word = content.getText().toString();
                url = url+word+url1;
            }
            @Override
            protected Boolean doInBackground(Object[] params) {
                try {
                    doc = Jsoup.connect(url).get();
                    word   = doc.select("key"). first().text();
                   phonetic  = doc.select("ps").first().text();
                    trans = doc.select("acceptation").first().text();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Object o) {
                Word.setText(word);
                Phonetic.setText(phonetic);
                Trans.setText(trans);
                super.onPostExecute(o);
            }
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(content.getText().toString())){
                    Toast.makeText(InternetSearch.this,"请填写要查询的单词",Toast.LENGTH_SHORT).show();
                    return;
                }
                WordTask task = new WordTask();
                task.execute();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.lenovo.wordbook.Word w=new Word(Word.getText().toString(),Trans.getText().toString());
                wordDataSQL.insert(wordDataSQL, w, "Word");
                Toast.makeText(InternetSearch.this,"添加成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
