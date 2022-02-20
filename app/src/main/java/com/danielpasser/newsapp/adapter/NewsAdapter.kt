package com.danielpasser.newsapp.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielpasser.newsapp.R
import com.danielpasser.newsapp.model.Article


class NewsAdapter(private val onTaskClick: (Article) -> Unit) : RecyclerView.Adapter<NewsViewHolder>() {

    private val articles: ArrayList<Article> = arrayListOf()
    private var isLoading = false;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return when (viewType) {

            R.layout.item_article -> NewsViewHolder.ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            )
            R.layout.item_download -> NewsViewHolder.DownloadItemViewHolder(
                LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }

    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder.ItemViewHolder -> {
                val article = if (isLoading) articles[position - 1] else articles[position]
                holder.bind(article,onTaskClick)
            }
        }
    }

    override fun getItemCount(): Int = if (isLoading) articles.size + 1 else articles.size

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == 0) R.layout.item_download
        else R.layout.item_article
    }

    fun updateData(articles: List<Article>) {
        this.articles.apply {
            clear()
            addAll(articles)
        }
        isLoading = false
        notifyDataSetChanged()
    }

    fun isLoading(isLoading: Boolean) {
        this.isLoading = true
        notifyDataSetChanged()
    }

}