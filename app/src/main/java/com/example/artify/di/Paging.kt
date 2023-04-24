package com.example.artify.di

import androidx.paging.PagingConfig
import com.example.artify.PAGESIZE
import com.example.artify.data.map.mappers.SearchedMapper
import com.example.artify.data.paging.SearchPagingSource
import com.example.artify.data.paging.SearchQuery
import com.example.artify.data.remote.MetService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Paging {
    @Singleton
    @Provides
    fun provideSearchPagingSource(
        metService: MetService,
        mapper: SearchedMapper,
        searchQuery: SearchQuery
    ): SearchPagingSource {
        return SearchPagingSource(metService, mapper, searchQuery)
    }

    @Singleton
    @Provides
    fun provideSearchQuery(): SearchQuery {
        return SearchQuery()
    }

    @Singleton
    @Provides
    fun providePagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = PAGESIZE, // Number of items to load at once
            enablePlaceholders = false // Disable null placeholders
        )
    }
}
