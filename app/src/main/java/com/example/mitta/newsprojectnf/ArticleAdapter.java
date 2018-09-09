package com.example.mitta.newsprojectnf;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.mitta.newsprojectnf.database.Article;
import com.master.glideimageview.GlideImageView;

import java.util.List;

class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articles;

    public interface OnItemClickListener{
        void onItemClick(Article article);
    }

    private OnItemClickListener listener;

    public ArticleAdapter(List<Article> articles, OnItemClickListener listener){
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {

        holder.articleRowView.setText(articles.get(position).getTitle());
        holder.timestampTextView.setText(articles.get(position).getTimestamp());
        holder.itemView.setTag(position);
        holder.glideImageView.loadImageUrl(articles.get(position).getThumbUrl());
        holder.bind(articles.get(position),listener);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public void addItems(List<Article> articleList){
        articles = articleList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView articleRowView;
        public TextView timestampTextView;
        public GlideImageView glideImageView;

        public ViewHolder(View itemView){
            super(itemView);
            articleRowView = itemView.findViewById(R.id.article_row_view);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
            glideImageView = itemView.findViewById(R.id.thumbImageView);
        }

        public void bind(final Article article, final OnItemClickListener listener){

            itemView.setOnClickListener(view -> listener.onItemClick(article));


        }
    }
}
