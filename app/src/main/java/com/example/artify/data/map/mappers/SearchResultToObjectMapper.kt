package com.example.artify.data.map.mappers

import com.example.artify.data.map.base.ObjectMapper
import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.artify.data.remote.dto.SearchResultDTO
import com.example.artify.data.model.SearchResult

class SearchResultToObjectMapper(override val failedMapperDelegate: FailedMapperDelegate) :
    ObjectMapper<SearchResultDTO, SearchResult> {
    override fun createModelFromDTO(input: Result<SearchResultDTO>): SearchResult? {
        val result = if (input.isSuccess) {
            input.getOrNull()?.objectIDs?.let { SearchResult(it) }
        }else{
            SearchResult(arrayListOf())
        }
        return result
    }
}