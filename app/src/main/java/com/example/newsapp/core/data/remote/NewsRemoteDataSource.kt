package com.example.newsapp.core.data.remote

import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val newsAPIServices: NewsAPIServices) :
    BaseRemoteDataSource() {
    suspend fun getNews() = getResult { newsAPIServices.getAllNews("apple", "1ab707fb07964a63ad740e788becc33b") }
}