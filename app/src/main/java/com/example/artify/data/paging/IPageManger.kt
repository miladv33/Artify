package com.example.artify.data.paging

import com.example.artify.model.base.SearchResult

interface IPageManger<T> {
    fun thereIsCachedData(lastQuery: String): Boolean
    fun getCachedDataFirstPage(): T?
    fun setCachedData(lastQuery: String, data: T?)
}