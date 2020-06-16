package com.example.ntinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ArticleFragment extends Fragment {
    private Docs Article;
    private Results TopArticles;
    private boolean needTop;

    public ArticleFragment(Docs d) {
        Article = d;
        needTop = false;
    }

    public ArticleFragment(Results d) {
        TopArticles = d;
        needTop = true;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (needTop) return inflater.inflate(R.layout.top_info, container, false);
        else return inflater.inflate(R.layout.article_info, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        TextView tv;
        String s;
        if (needTop) {
            tv = getActivity().findViewById(R.id.hdline2);
            s = TopArticles.getTitle();
            tv.setText(s);
            tv = getActivity().findViewById(R.id.am);
            s = TopArticles.getPublished_date();
            tv.setText(tv.getText() + s);
            tv = getActivity().findViewById(R.id.Source);
            s = TopArticles.getSection();
            tv.setText(tv.getText() + s);
            tv = getActivity().findViewById(R.id.MatType);
            s = TopArticles.getItem_type();
            tv.setText(tv.getText() + s);
            tv = getActivity().findViewById(R.id.MainBody);
            s = TopArticles.getAbstract();
            tv.setText(s);
            tv = getActivity().findViewById(R.id.link);
            s = TopArticles.getUrl();
            tv.setText(tv.getText() + s);
        } else {
            tv = getActivity().findViewById(R.id.hdline);
            s = Article.getHeadline().getMain();
            tv.setText(s);
            tv = getActivity().findViewById(R.id.am);
            s = Article.getWord_count();
            tv.setText(tv.getText() + s);
            tv = getActivity().findViewById(R.id.Source);
            s = Article.getSource();
            tv.setText(tv.getText() + s);
            tv = getActivity().findViewById(R.id.Type);
            s = Article.getDocument_type();
            tv.setText(tv.getText() + s);
            tv = getActivity().findViewById(R.id.MatType);
            s = Article.getType_of_material();
            tv.setText(tv.getText() + s);
            tv = getActivity().findViewById(R.id.MainBody);
            s = Article.getLead_paragraph();
            tv.setText(s);
            tv = getActivity().findViewById(R.id.link);
            s = Article.getWeb_url();
            tv.setText(tv.getText() + s);
        }
    }
}
