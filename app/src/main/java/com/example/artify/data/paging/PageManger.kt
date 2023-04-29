package com.example.artify.data.paging

import com.example.artify.model.base.SearchResult

class PageManger : IPageManger<List<Int>> {

    private var _searchResult: ArrayList<Int>? = null
    private var searchResult: List<Int>? = _searchResult
    private var lastQuery: String? = null

    override fun thereIsCachedData(lastQuery: String): Boolean {
        return this.lastQuery == lastQuery && searchResult != null
    }

    override fun getCachedDataFirstPage(): List<Int>? {
        val result = searchResult?.take(40)
        result?.let { _searchResult?.removeAll(it) }
        return result
    }

    override fun setCachedData(lastQuery: String, data: List<Int>?) {
        this.lastQuery = lastQuery
        this.searchResult = data
    }
}