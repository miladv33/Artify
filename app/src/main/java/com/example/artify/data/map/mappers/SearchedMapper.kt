package com.example.artify.data.map.mappers

import com.example.artify.data.map.base.ResponseMapper
import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.artify.data.remote.dto.SearchResultDTO
import com.example.artify.model.base.SearchResult
import retrofit2.Response
import javax.inject.Inject

class SearchedMapper @Inject constructor(
    override val failedMapperDelegate: FailedMapperDelegateImpl
) : ResponseMapper<SearchResultDTO, SearchResult>, FailedMapperDelegate by failedMapperDelegate {
    override fun createModelFromDTO(input: Response<SearchResultDTO>): SearchResult {
        return input.body()?.let {
            SearchResult(it.objectIDs)
        }.run {
            SearchResult(arrayListOf())
        }
    }
}