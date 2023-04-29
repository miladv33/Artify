package com.example.artify

import androidx.lifecycle.MutableLiveData
import com.example.artify.data.paging.PageManger
import com.example.artify.model.base.SearchResult
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PageManagerTest {

    private lateinit var pageManager: PageManger

    @Before
    fun setUp() {
        pageManager = PageManger()
    }

    @Test
    fun thereIsCachedData_returnsTrue_whenLastQueryMatchesAndSearchResultIsNotNull() {
        val lastQuery = "test"
        val data = listOf(1, 2, 3)
        pageManager.setCachedData(lastQuery, data)

        val result = pageManager.thereIsCachedData(lastQuery)

        assertEquals(true, result)
    }

    @Test
    fun thereIsCachedData_returnsFalse_whenLastQueryDoesNotMatchOrSearchResultIsNull() {
        val lastQuery = "test"
        val data = listOf(1, 2, 3)
        pageManager.setCachedData(lastQuery, data)

        assertEquals(false, pageManager.thereIsCachedData("other"))
        pageManager.setCachedData(lastQuery, null)
        assertEquals(false, pageManager.thereIsCachedData(lastQuery))
    }


    @Test
    fun getCachedDataFirstPage_returnsNull_whenSearchResultIsNull() {
        pageManager.setCachedData("test", null)
        val result = pageManager.getCachedDataFirstPage()
        assertEquals(null, result)
    }

    @Test
    fun setCachedData_setsLastQueryAndSearchResult() {
        val lastQuery = "test"
        val data = listOf(1, 2, 3)
        pageManager.setCachedData(lastQuery, data)

        assertEquals(lastQuery, pageManager.lastQuery)
        assertEquals(data, pageManager.searchResult)
    }
}