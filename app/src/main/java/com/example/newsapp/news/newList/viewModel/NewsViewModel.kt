package com.example.newsapp.news.newList.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.newsapp.core.data.repository.NewsRepository

class NewsViewModel @ViewModelInject constructor(private val repository: NewsRepository) :ViewModel() {
    val newsList = repository.getNewsList()
}