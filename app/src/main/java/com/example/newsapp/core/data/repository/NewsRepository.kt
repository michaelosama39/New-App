package com.example.newsapp.core.data.repository

import com.example.newsapp.core.data.local.NewsLocalDataSource
import com.example.newsapp.core.data.remote.NewsRemoteDataSource
import com.example.newsapp.core.utils.performGetOperation
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) {


    fun getNewsList() = performGetOperation(
                                             dataBaseQuery = { newsLocalDataSource.getNews() },
                                             netWorkCall = { newsRemoteDataSource.getNews() },
                                             saveCallResult = { newsLocalDataSource.insertNews(it.articles) })
}