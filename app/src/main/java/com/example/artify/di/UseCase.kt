package com.example.artify.di

import androidx.paging.PagingConfig
import com.example.artify.data.SearchRepository
import com.example.artify.data.paging.SearchPagingSource
import com.example.artify.domain.usecase.SearchUseCaseImpl
import com.example.artify.domain.usecase.SearchUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCase {

    @Singleton
    @Provides
    fun provideSearchUseCase(
        repository: SearchRepository
    ): SearchUserCase {
        return SearchUseCaseImpl(repository)
    }
}