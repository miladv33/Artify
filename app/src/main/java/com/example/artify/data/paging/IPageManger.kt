package com.example.artify.data.paging

import androidx.lifecycle.MutableLiveData
import com.example.artify.data.model.SearchResult

interface IPageManger<T> {
    fun thereIsCachedData(lastQuery: String): Boolean
    fun getCachedDataFirstPage(): T?
    fun setCachedData(lastQuery: String, data: T?)
    fun handleSearchSuccess(
        query: String,
        searchResult: SearchResult,
        _searchResult: MutableLiveData<ArrayList<Int>>
    )
    fun getCashedData(_searchResult: MutableLiveData<ArrayList<Int>>)
}