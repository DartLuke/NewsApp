package com.danielpasser.newsapp.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.danielpasser.newsapp.R
import com.danielpasser.newsapp.model.Article

class NewsFragment : Fragment() {

    private val safeArgs: NewsFragmentArgs by navArgs()
    private val article: Article by lazy { safeArgs.article }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            findViewById<TextView>(R.id.text_view_title).text = article.title
            findViewById<TextView>(R.id.text_view_description).text = article.description
            Glide.with(context).load(article.urlToImage)
                .into(findViewById(R.id.image_view))
        }
    }


}