package com.example.artify.di

import androidx.paging.PagingConfig
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
    fun provideSearchRepository(
        searchPagingSource: SearchPagingSource,
        pagingConfig: PagingConfig
    ): SearchRepository {
        return SearchRepository(searchPagingSource, pagingConfig)
    }
}