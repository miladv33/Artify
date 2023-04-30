package com.example.artify.data.repository

import com.example.artify.data.model.MetObjectData
import com.example.artify.data.model.SearchResult

interface Repository {

    interface SearchRepository {
        suspend fun getSearchResults(query: String): Result<SearchResult>
    }

    interface MetObjectDataRepository {
        suspend fun getMetObjectData(objectData: Int): Result<MetObjectData>
    }
}