package com.example.lenovo.wordbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.word_title_frag, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.word_content_layout) != null) {
            isTwoPane = true; // 可以找到word_content_layout布局时，为双页模式
        } else {
            isTwoPane = false; // 找不到word_content_layout布局时，为单页模式
        }
    }



   class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

        private List<Word> mWordList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView wordTitleText;

            public ViewHolder(View view) {
                super(view);
                wordTitleText = (TextView) view.findViewById(R.id.word_title);
            }

        }

        public WordAdapter(List<Word> wordList) {
            mWordList = wordList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Word word = mWordList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        WordContentFragment newsContentFragment = ( WordContentFragment)
                                getFragmentManager().findFragmentById(R.id.word_content_fragment);
                        newsContentFragment.refresh(word.getWord(), word.getContent());
                    } else {
                        WordContentActivity.actionStart(getActivity(), word.getWord(), word.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Word word = mWordList.get(position);
            holder.wordTitleText.setText(word.getWord()+" "+word.getContent());
        }

        @Override
        public int getItemCount() {
            return mWordList.size();
        }

    }

}
