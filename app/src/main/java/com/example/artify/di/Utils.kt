package com.example.artify.di

import com.example.artify.presentation.loading.Loading
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Utils {

    @Provides
    fun provideLoading(): Loading {
        return Loading()
    }
}