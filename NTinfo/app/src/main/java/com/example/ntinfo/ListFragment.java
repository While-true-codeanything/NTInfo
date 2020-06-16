package com.example.ntinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment {
    private ArticleRespoce Data;
    private boolean needTop;
    private TopResponse Data2;

    public ListFragment(ArticleRespoce r) {
        Data = r;
        needTop = false;
    }

    public ListFragment(TopResponse r) {
        Data2 = r;
        needTop = true;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onStart() {
        RecyclerView res = getActivity().findViewById(R.id.list);
        if (needTop) {
            res.setAdapter(new ListAdapter(Data2, getContext()));
        } else {
            res.setAdapter(new ListAdapter(Data, getContext()));
        }
        super.onStart();
    }
}
