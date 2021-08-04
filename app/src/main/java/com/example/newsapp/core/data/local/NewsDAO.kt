package com.example.newsapp.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.news.newList.model.Article

@Dao
interface NewsDAO {

    //Get
    @Query("select * from newsList")
    fun getAllNews(): LiveData<List<Article>>

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: ArrayList<Article>)

    //delete
    @Query("delete from newsList WHERE id ==:newsId")
    fun deleteById(newsId: Int)

}