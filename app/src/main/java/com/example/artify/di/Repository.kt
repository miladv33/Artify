package com.example.artify.di

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.artify.data.map.mappers.ObjectDataMapper
import com.example.artify.data.map.mappers.SearchedMapper
import com.example.artify.data.paging.PageManger
import com.example.artify.data.remote.MetService
import com.example.artify.data.repository.MetObjectDataRepository
import com.example.artify.data.repository.SearchRepository
import com.example.artify.data.repository.safecall.SafeCallDelegateImpl
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
        metService: MetService,
        mapper: SearchedMapper,
        safeCallDelegate: SafeCallDelegateImpl,
        pageManger: PageManger
    ): SearchRepository {
        return SearchRepository(metService, mapper, safeCallDelegate, pageManger)
    }

    @Provides
    @Singleton
    fun provideMetObjectRepository(
        metService: MetService,
        mapper: ObjectDataMapper,
        safeCallDelegate: SafeCallDelegateImpl
    ): MetObjectDataRepository {
        return MetObjectDataRepository(metService, mapper, safeCallDelegate)
    }

    @Provides
    @Singleton
    fun provideSafeCallDelegate(failedMapperDelegate: FailedMapperDelegate): SafeCallDelegateImpl {
        return SafeCallDelegateImpl(failedMapperDelegate)
    }

}