package com.danielpasser.newsapp.adapter


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielpasser.newsapp.R
import com.danielpasser.newsapp.model.Article


sealed class NewsViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    class DownloadItemViewHolder(item: View) : NewsViewHolder(item) 

    class ItemViewHolder(item: View) : NewsViewHolder(item) {
        fun bind(article: Article, onTaskClick: (Article) -> Unit) {
            itemView.apply {
                findViewById<TextView>(R.id.text_view_title).text = article.title
                Glide.with(context).load(article.urlToImage)
                    .into(findViewById(R.id.image_view))
                setOnClickListener{
                    onTaskClick.invoke(article)
                }
            }


        }
    }
}
