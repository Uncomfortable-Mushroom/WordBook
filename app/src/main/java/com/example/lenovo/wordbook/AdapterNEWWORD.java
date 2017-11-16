package com.example.lenovo.wordbook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/10/17.
 */

public class AdapterNEWWORD extends RecyclerView.Adapter<AdapterNEWWORD.ViewHolder> {

    private List<Word> mWordList;
    @Override
    public AdapterNEWWORD.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterNEWWORD.ViewHolder holder, int position) {
        Word word=mWordList.get(position);
        holder.wordTitleText.setText(word.getWord()+" "+word.getContent());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public AdapterNEWWORD(List<Word> list){
        mWordList=list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordTitleText;
        public ViewHolder(View itemView) {
            super(itemView);
            wordTitleText = (TextView) itemView.findViewById(R.id.word_title);
        }
    }
}
