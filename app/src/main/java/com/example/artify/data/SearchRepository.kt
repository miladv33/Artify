package com.example.artify.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.artify.data.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val pagingSource: SearchPagingSource,
    private val pagingConfig: PagingConfig
){
    fun getSearchResults(query: String): Flow<PagingData<Int>> {
        val pagingSource = pagingSource
        pagingSource.setQuery(query)
        return Pager(pagingConfig) {
            pagingSource
        }.flow
    }
}