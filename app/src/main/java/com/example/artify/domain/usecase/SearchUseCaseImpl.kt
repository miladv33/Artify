package com.example.artify.domain.usecase

import com.example.artify.data.repository.SearchRepository
import com.example.artify.data.remote.dto.SearchResultDTO
import com.example.artify.model.base.SearchResult
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
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