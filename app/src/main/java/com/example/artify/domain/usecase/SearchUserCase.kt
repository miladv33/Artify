package com.example.artify.domain.usecase

import androidx.paging.PagingData
import com.example.artify.data.remote.dto.SearchResultDTO
import com.example.artify.model.base.SearchResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SearchUserCase {
    fun search(query: String): Flow<Result<SearchResult>>
}