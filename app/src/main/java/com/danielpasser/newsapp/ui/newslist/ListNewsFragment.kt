package com.danielpasser.newsapp.ui.newslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danielpasser.newsapp.R
import com.danielpasser.newsapp.adapter.NewsAdapter
import com.danielpasser.newsapp.model.Article
import com.danielpasser.newsapp.ui.decorator.ItemDecorator
import com.danielpasser.newsapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNewsFragment : Fragment() {

    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val newsAdapter = NewsAdapter( onTaskClick = {
        onTaskClick(it)
    },)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupAdapter(view)
        viewModel.downLoadArticles()
    }

    private fun setupAdapter(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_movies)

        recyclerView.apply {

            layoutManager = LinearLayoutManager(view.context)
            addItemDecoration(ItemDecorator(10))
            adapter = newsAdapter
        }

    }

    private fun setupObservers() {
        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Success<List<Article>> -> {
                    newsAdapter.updateData(dataState.data)
                }
                is DataState.Error -> {

                    displayError(dataState.exception?.message)
                }
                is DataState.Loading -> {
                    newsAdapter.isLoading(true)

                }
            }

        }
    }

    private fun displayError(message: String?) {
        if (message == null)
            Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }
    private fun onTaskClick(article: Article)
    {
val nav=ListNewsFragmentDirections.actionListNewsFragmentToNewsFragment(article)
        findNavController().navigate(nav)
    }
}
