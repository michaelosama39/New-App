package com.example.newsapp.news.newList.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class NewsResponseModel(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)

const val NEWS_ID = 0

@Entity(tableName = "newsList")
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = NEWS_ID
}

data class Source(
    val id: String,
    val name: String
)