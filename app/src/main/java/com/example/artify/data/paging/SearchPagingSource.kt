package com.example.artify.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.artify.data.enum.Error
import com.example.artify.data.map.mappers.SearchedMapper
import com.example.artify.data.remote.MetService
import com.example.artify.model.base.CustomException
import com.example.artify.model.base.SearchResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import com.squareup.inject.assisted.AssistedInject.Factory
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val apiService: MetService,
    private val searchedMapper: SearchedMapper,
    private val searchQuery: SearchQuery // Injecting SearchQuery class
) : PagingSource<Int, Int>() {
    private var cachedData: Result<SearchResult>? = null
    private var total: Int = 0

    fun setQuery(query: String) {
        cachedData = null
        searchQuery.setQuery(query)
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {
        val page = params.key ?: 1 // start from page 1 if null
        return try {
            if (cachedData == null) {
                val searchObjects = apiService.searchObjects(searchQuery.getQuery())
                cachedData = searchedMapper.map(searchObjects)
                if (searchObjects?.isSuccessful == true && searchObjects.body() != null) {
                    total = searchObjects.body()?.total ?: 0
                }
            }
            if (cachedData?.isSuccess == true) {
                LoadResult.Page(
                    data = cachedData?.getOrNull()?.objectIDs ?: arrayListOf(),
                    prevKey = if (page == 1) null else page - 1, // null if first page
                    nextKey = if (page == total) null else page + 1 // null if last page
                )
            } else {
                LoadResult.Error(CustomException(Error.NullObject))
            }
        } catch (e: Exception) {
            LoadResult.Error(e) // return error if exception occurs
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Int>): Int? {
        return state.anchorPosition
    }

}