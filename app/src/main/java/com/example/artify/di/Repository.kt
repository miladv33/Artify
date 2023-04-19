package com.example.artify.di

import com.example.artify.data.SearchRepository
import com.example.artify.data.paging.SearchPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Repository {


    @Provides
    @Singleton
    fun provideSearchRepository(searchPagingSource: SearchPagingSource): SearchRepository {
        return SearchRepository(searchPagingSource)
    }
}