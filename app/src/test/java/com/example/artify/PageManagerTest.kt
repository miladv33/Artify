package com.example.artify

import com.example.artify.data.paging.PageManger
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

        val result = pageManager.hasCachedData(lastQuery)

        assertEquals(true, result)
    }

    @Test
    fun thereIsCachedData_returnsFalse_whenLastQueryDoesNotMatchOrSearchResultIsNull() {
        val lastQuery = "test"
        val data = listOf(1, 2, 3)
        pageManager.setCachedData(lastQuery, data)

        assertEquals(false, pageManager.hasCachedData("other"))
        pageManager.setCachedData(lastQuery, null)
        assertEquals(false, pageManager.hasCachedData(lastQuery))
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