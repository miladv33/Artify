package com.example.artify.di

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

}
