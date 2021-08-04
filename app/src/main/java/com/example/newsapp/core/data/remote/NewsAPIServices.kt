package com.example.newsapp.core.data.remote

import com.example.newsapp.news.newList.model.NewsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIServices {
    @GET("everything")
   suspend fun getAllNews(
        @Query("q") topic: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponseModel>
}