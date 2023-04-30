package com.example.artify.di

import com.example.artify.data.repository.MetObjectDataRepository
import com.example.artify.data.repository.Repository
import com.example.artify.data.repository.SearchRepository
import com.example.artify.domain.usecase.metobject.MetObjectUseCase
import com.example.artify.domain.usecase.metobject.MetObjectUseCaseImpl
import com.example.artify.domain.usecase.search.SearchUseCaseImpl
import com.example.artify.domain.usecase.search.SearchUserCase
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

    @Singleton
    @Provides
    fun provideMetObjectUseCase(metObjectDataRepository: MetObjectDataRepository): MetObjectUseCase {
        return MetObjectUseCaseImpl(metObjectDataRepository)
    }
}