package com.example.artify.di

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.artify.data.map.mappers.SearchedMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Mapper {

    @Singleton
    @Provides
    fun provideSearchMapper(failedMapperDelegate: FailedMapperDelegateImpl): SearchedMapper {
        return SearchedMapper(failedMapperDelegate)
    }

    @Provides
    fun provideFailedMapperDelegate(): FailedMapperDelegate {
        return FailedMapperDelegateImpl()
    }
}