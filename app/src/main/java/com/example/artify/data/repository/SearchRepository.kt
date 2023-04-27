package com.example.artify.data.repository

import com.example.artify.data.map.mappers.SearchedMapper
import com.example.artify.data.paging.PageManger
import com.example.artify.data.remote.MetService
import com.example.artify.data.repository.safecall.SafeCallDelegate
import com.example.artify.data.repository.safecall.SafeCallDelegateImpl
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiService: MetService,
    private val mapper: SearchedMapper,
    private val safeCallDelegate: SafeCallDelegateImpl
) : Repository.SearchRepository, SafeCallDelegate by safeCallDelegate {
    override suspend fun getSearchResults(query: String) = safeCall {
        val searchObjects = apiService.searchObjects(query)
        val map = mapper.map(searchObjects)
        map
    }
}