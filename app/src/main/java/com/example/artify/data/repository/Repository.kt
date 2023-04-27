package com.example.artify.data.repository

import com.example.artify.data.paging.SearchQuery
import com.example.artify.data.remote.dto.SearchResultDTO
import com.example.artify.model.base.SearchResult
import kotlinx.coroutines.flow.Flow

interface Repository {

    interface SearchRepository {
        suspend fun getSearchResults(query: String): Result<SearchResult>
    }
}