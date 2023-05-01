package com.example.artify.data.repository

import androidx.room.PrimaryKey
import com.example.artify.data.map.mappers.SearchedMapper
import com.example.artify.data.paging.PageManger
import com.example.artify.data.remote.MetService
import com.example.artify.data.repository.safecall.SafeCallDelegate
import com.example.artify.data.repository.safecall.SafeCallDelegateImpl
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiService: MetService,
    private val mapper: SearchedMapper,
    private val safeCallDelegate: SafeCallDelegateImpl,
    private val pageManger: PageManger,
) : Repository.SearchRepository, SafeCallDelegate by safeCallDelegate {
    override suspend fun getSearchResults(query: String) = safeCall {
        if (!pageManger.hasCachedData(query)) {
            val searchObjects = apiService.searchObjects(query)
            val result = mapper.map(searchObjects)
            pageManger.setCachedData(query, result.getOrNull()?.objectIDs)
        }
        pageManger.loadCachedData()
    }
}