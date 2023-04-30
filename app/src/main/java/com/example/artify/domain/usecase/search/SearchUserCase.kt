package com.example.artify.domain.usecase.search

import com.example.artify.data.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchUserCase {
    fun search(query: String): Flow<Result<SearchResult>>
}