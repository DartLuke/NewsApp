package com.danielpasser.newsapp.api

import com.danielpasser.newsapp.model.NewsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("top-headlines")
    suspend fun getNews(@Query("apiKey") api_key: String,@Query("country") country:String):NewsResult
}