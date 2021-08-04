package com.example.newsapp.core.data.local

import com.example.newsapp.news.newList.model.Article
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDAO: NewsDAO) {
    fun getNews() = newsDAO.getAllNews()
    fun insertNews(articles: ArrayList<Article>) = newsDAO.insert(articles)
}