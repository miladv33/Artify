package com.example.artify.data.paging

import javax.inject.Inject

class SearchQuery @Inject constructor() {
    private var currentQuery: String? = null

    fun setQuery(query: String) {
        currentQuery = query
    }

    fun getQuery(): String? {
        return currentQuery
    }
}