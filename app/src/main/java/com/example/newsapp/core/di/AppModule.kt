package com.example.newsapp.core.di

import android.content.Context
import com.example.newsapp.core.data.local.AppDatabaseBuilder
import com.example.newsapp.core.data.local.NewsDAO
import com.example.newsapp.core.data.local.NewsLocalDataSource
import com.example.newsapp.core.data.remote.AppNetworkBuilder
import com.example.newsapp.core.data.remote.NewsAPIServices
import com.example.newsapp.core.data.remote.NewsRemoteDataSource
import com.example.newsapp.core.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppNetworkBuilder(): Retrofit = AppNetworkBuilder().provideRetrofitClient()

    @Singleton
    @Provides
    fun provideAppDataBaseBuilder(@ApplicationContext context: Context) =
        AppDatabaseBuilder.provideRoomDatabase(context)


    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsAPIServices =
        retrofit.create(NewsAPIServices::class.java)

    @Provides
    fun provideNewsDao(appDatabaseBuilder: AppDatabaseBuilder) = appDatabaseBuilder.getNews()


    @Provides
    fun provideNewsRemoteDataSource(newsAPIServices: NewsAPIServices) =
        NewsRemoteDataSource(newsAPIServices)

    @Provides
    fun provideNewsLocalDataSource(newsDAO: NewsDAO) = NewsLocalDataSource(newsDAO)

    @Provides
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource,
        localDataSource: NewsLocalDataSource) =
        NewsRepository(remoteDataSource, localDataSource)

}