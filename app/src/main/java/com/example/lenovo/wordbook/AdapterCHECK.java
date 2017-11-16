package com.example.lenovo.wordbook;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class AdapterCHECK extends RecyclerView.Adapter<AdapterCHECK.XViewHolder> {
    private List<Word> mWordList;
    @Override
    public XViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_item,parent,false);
        final XViewHolder holder=new XViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = mWordList.get(holder.getAdapterPosition());
                WordContentActivity.actionStart(view.getContext(), word.getWord(), word.getContent());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(XViewHolder holder, int position) {
         Word word=mWordList.get(position);
         holder.wordTitleText.setText(word.getWord()+" "+word.getContent());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public AdapterCHECK(List<Word> list){
        mWordList=list;
    }


    static class  XViewHolder extends RecyclerView.ViewHolder{
        TextView wordTitleText;
        public XViewHolder(View itemView) {
            super(itemView);
            wordTitleText = (TextView) itemView.findViewById(R.id.word_title);
        }
    }
}
