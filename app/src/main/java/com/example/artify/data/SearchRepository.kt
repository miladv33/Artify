package com.example.artify.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.artify.data.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchPagingSourceFactory: SearchPagingSource
){
    fun getSearchResults(query: String): Flow<PagingData<Int>> {
        val pagingSource = searchPagingSourceFactory
        pagingSource.setQuery(query)
        return Pager(PagingConfig(pageSize = 20)) {
            pagingSource
        }.flow
    }
}