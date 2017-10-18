package com.example.lenovo.wordbook;

public class Word {
    private String word;
    private String content;
    public Word(String s,String s1){
        this.word=s;this.content=s1;
    }
    public String getWord(){
        return word;
    }
    public void setWord(String word){
        this.word=word;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }
}
