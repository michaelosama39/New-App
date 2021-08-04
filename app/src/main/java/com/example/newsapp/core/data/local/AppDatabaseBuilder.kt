package com.example.newsapp.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.news.newList.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabaseBuilder : RoomDatabase() {


    abstract fun getNews(): NewsDAO

    companion object {
        private var instance: AppDatabaseBuilder? = null


        fun provideRoomDatabase(appContext: Context): AppDatabaseBuilder =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(appContext).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabaseBuilder::class.java, "newsAppDB")
                .fallbackToDestructiveMigration()
                .build()
    }

}