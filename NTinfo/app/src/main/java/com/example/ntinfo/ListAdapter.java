package com.example.ntinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArticleRespoce Data;
    private TopResponse Data2;
    public LayoutInflater inflater;
    private Context context;
    private boolean needTop;

    public ListAdapter(ArticleRespoce a, Context context) {
        Data = a;
        needTop = false;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public ListAdapter(TopResponse a, Context context) {
        Data2 = a;
        this.context = context;
        needTop = true;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View subj = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(subj);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        final String s;
        if (needTop) {
            s = Data2.getResults()[position].getTitle();
        } else {
            s = Data.getResponse().getDocs()[position].getHeadline().getMain();
        }
        holder.HeadLine.setText(s);
        View.OnClickListener next = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needTop)
                    MainActivity.loadFragment(new ArticleFragment(Data2.getResults()[position]));
                else
                    MainActivity.loadFragment(new ArticleFragment(Data.getResponse().getDocs()[position]));
            }
        };
        holder.HeadLine.setOnClickListener(next);
    }

    @Override
    public int getItemCount() {
        if (needTop) return Data2.getResults().length;
        else return Data.getResponse().getDocs().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView HeadLine;

        ViewHolder(View view) {
            super(view);
            HeadLine = view.findViewById(R.id.headline);
        }
    }
}
