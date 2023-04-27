package com.example.artify

import androidx.paging.PagingData
import com.example.artify.data.repository.SearchRepository
import com.example.artify.data.paging.SearchPagingSource
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest {

    private lateinit var searchPagingSourceFactory: SearchPagingSource
    private lateinit var searchRepository: SearchRepository

    @Before
    fun setup() {
        searchPagingSourceFactory = mockk(relaxed = true)
        searchRepository = SearchRepository(searchPagingSourceFactory)
    }

    @Test
    fun `getSearchResults returns Flow of PagingData`() = runBlocking {
        // given
        val query = "example"
        val pagingSource = mockk<SearchPagingSource>()
        val pagingData = PagingData.from(listOf(1, 2, 3))
        coEvery { pagingSource.setQuery(query) } just Runs
        every { searchPagingSourceFactory } returns pagingSource

        // when
        val result = searchRepository.getSearchResults(query)

        // then
        result.collectLatest { data ->
            assertEquals(pagingData, data)
        }
    }
}
