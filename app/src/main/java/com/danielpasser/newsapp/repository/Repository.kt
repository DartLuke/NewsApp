package com.danielpasser.newsapp.repository


import android.util.Log
import com.danielpasser.newsapp.api.Api
import com.danielpasser.newsapp.model.Article
import com.danielpasser.newsapp.model.NewsResult
import com.danielpasser.newsapp.utils.Constants
import com.danielpasser.newsapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository constructor(
    private val retrofit: Api,
) {
    suspend fun getNews(): Flow<DataState<List<Article>>> = flow {
        emit(DataState.Loading)
        try {
            val news = downloadNews().articles
            emit(DataState.Success(news))
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emit(DataState.Error(e))
        }
    }

    private suspend fun downloadNews(): NewsResult {
        return retrofit.getNews(api_key = Constants.API_KEY, country = "us")
    }


}