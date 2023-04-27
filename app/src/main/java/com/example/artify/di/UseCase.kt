package com.example.artify.di

import com.example.artify.data.repository.SearchRepository
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