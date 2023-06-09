package com.example.artify.data.paging

import androidx.lifecycle.MutableLiveData
import com.example.artify.data.model.SearchResult

/* The PageManager class implements the IPageManager interface to manage and cache search results. */
class PageManger : IPageManger<List<Int>> {

    private var _searchResult: ArrayList<Int>? = ArrayList()
    var searchResult: List<Int>? = _searchResult
    var lastQuery: String? = null

    override fun hasCachedData(lastQuery: String): Boolean {
        return this.lastQuery == lastQuery && searchResult != null
    }

    override fun getCachedDataFirstPage(): List<Int>? {
        val result = searchResult?.take(40)
        result?.let {
            _searchResult?.removeAll(result)
        }
        return result
    }

    override fun setCachedData(lastQuery: String, data: List<Int>?) {
        this.lastQuery = lastQuery.trimEnd()
        this._searchResult?.clear()
        if (data != null) {
            this._searchResult?.addAll(data)
        }
    }


    override fun loadCachedData(): Result<SearchResult> {
        val newList = ArrayList<Int>() // create a new list
        newList.addAll(
            getCachedDataFirstPage() ?: emptyList()
        )
        val hasMoreData = _searchResult?.isNotEmpty() ?: false
        return Result.success(SearchResult(newList, hasMoreData))
    }
}