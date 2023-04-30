package com.example.artify.domain.usecase

import com.example.artify.data.repository.SearchRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class SearchUseCaseImpl @Inject constructor(
    private val repository: SearchRepository
) : SearchUserCase {
    override fun search(query: String) = flow {
        emit(repository.getSearchResults(query))
    }
}