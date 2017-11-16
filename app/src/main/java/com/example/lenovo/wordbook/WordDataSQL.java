package com.example.lenovo.wordbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WordDataSQL extends SQLiteOpenHelper{
    private static final String CREATE_WORD="create table Word(" +
            "id integer primary key autoincrement," +
            "word text," +
            "content text)" ;
    private static final String CREATE_NEWWORD="create table NewWord(" +
            "id integer primary key autoincrement," +
            "word text," +
            "content text)" ;
    public WordDataSQL(Context context,String name,
                       SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_WORD);
        sqLiteDatabase.execSQL(CREATE_NEWWORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void insert(WordDataSQL wordDataSQL,Word word,String s) {
        SQLiteDatabase sqLiteDatabase = wordDataSQL.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("word", word.getWord());
        values.put("content", word.getContent());
        sqLiteDatabase.insert(s, null, values);
        values.clear();

    }
    public List<Word> getall(WordDataSQL wordDataSQL,String s){
        List<Word> mword = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=wordDataSQL.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(s,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Word word=new Word(null,null);
                word.setWord(cursor.getString(cursor.getColumnIndex("word")));
                word.setContent(cursor.getString(cursor.getColumnIndex("content")));
                mword.add(word);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return mword;
    }
    public void clear(WordDataSQL wordDataSQL){
        SQLiteDatabase sqLiteDatabase=wordDataSQL.getWritableDatabase();
        sqLiteDatabase.execSQL("drop table if exists Word");
        sqLiteDatabase.execSQL("drop table if exists NewWord");
        sqLiteDatabase.execSQL(CREATE_WORD);
        sqLiteDatabase.execSQL(CREATE_NEWWORD);
    }
    public void delete(WordDataSQL wordDataSQL,Word word,String s){
        SQLiteDatabase sqLiteDatabase=wordDataSQL.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+s+" where word='"+word.getWord()+"'");
    }
    public boolean isEmpty(WordDataSQL wordDataSQL,String s){
        SQLiteDatabase sqLiteDatabase=wordDataSQL.getWritableDatabase();
        Cursor c =  sqLiteDatabase.rawQuery("select * from "+s, null);
        int amount=c.getCount();
        if(amount==0)
            return true;
        return false;
    }
}
