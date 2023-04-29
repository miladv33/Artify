package com.example.artify.di

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.artify.presentation.error.ShowDialogDelegateImpl
import com.example.artify.presentation.error.ShowErrorDelegate
import com.example.artify.presentation.loading.LoadingDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Delegate {
    @Provides
    fun provideFailedMapperDelegateImpl(): FailedMapperDelegateImpl {
        return FailedMapperDelegateImpl()
    }

    @Provides
    fun provideShowErrorDelegate(): ShowErrorDelegate {
        return ShowDialogDelegateImpl()
    }

    @Provides
    fun provideLoading(): LoadingDelegate {
        return LoadingDelegate()
    }
}