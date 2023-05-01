package com.example.artify.di

import com.example.artify.data.paging.PageManger
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
    fun providePageManger(): PageManger {
        return PageManger()
    }
}
