package com.example.artify.data.paging

import androidx.lifecycle.MutableLiveData
import com.example.artify.data.model.SearchResult

class PageManger : IPageManger<List<Int>> {

    private var _searchResult: ArrayList<Int>? = null
    var searchResult: List<Int>? = _searchResult
    var lastQuery: String? = null

    override fun thereIsCachedData(lastQuery: String): Boolean {
        return this.lastQuery == lastQuery && searchResult != null
    }

    override fun getCachedDataFirstPage(): List<Int>? {
        val result = searchResult?.take(40)
        result?.let { _searchResult?.removeAll(it) }
        return result
    }

    override fun setCachedData(lastQuery: String, data: List<Int>?) {
        this.lastQuery = lastQuery.trimEnd()
        this.searchResult = data
    }


    override fun handleSearchSuccess(
        query: String,
        searchResult: SearchResult,
        _searchResult: MutableLiveData<ArrayList<Int>>
    ) {
        setCachedData(
            query,
            searchResult.objectIDs
        )
        getCashedData(_searchResult)
    }

    override fun getCashedData(_searchResult: MutableLiveData<ArrayList<Int>>) {
        val newList = ArrayList<Int>() // create a new list
        if (_searchResult.value.isNullOrEmpty()) {
            newList.addAll(
                getCachedDataFirstPage() ?: emptyList()
            ) // add the first page to the new list
        } else {
            newList.addAll(
                _searchResult.value ?: emptyList()
            ) // add the current value to the new list
            newList.addAll(
                getCachedDataFirstPage() ?: emptyList()
            ) // add the next page to the new list
        }
        _searchResult.value = newList // assign the new list to the _searchResult.value
    }
}