package com.example.artify

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.artify.data.map.mappers.SearchedMapper
import com.example.artify.data.remote.dto.SearchResultDTO
import com.example.artify.model.base.CustomException
import com.example.artify.model.base.SearchResult
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import com.example.artify.data.enum.Error

class SearchedMapperTest {

    private lateinit var searchedMapper: SearchedMapper
    private lateinit var failedMapperDelegate: FailedMapperDelegateImpl

    @Before
    fun setUp() {
        failedMapperDelegate = spyk(FailedMapperDelegateImpl())
        searchedMapper = spyk(SearchedMapper(failedMapperDelegate))
    }

    @Test
    fun `test map when response is successful`() {
        val objectIDs = listOf(1, 2, 3)
        val total = 3
        val searchResultDTO = SearchResultDTO(objectIDs, total)
        val response = Response.success(searchResultDTO)

        val expectedResult = SearchResult(objectIDs)
        every { searchedMapper.createModelFromDTO(response) } returns expectedResult

        val result = searchedMapper.map(response)

        verify { searchedMapper.createModelFromDTO(response) }
        assertEquals(Result.success(expectedResult), result)
    }

    @Test
    fun `test map when response is null`() {
        val response: Response<SearchResultDTO>? = null
        val expectedException = CustomException(Error.NullObject)

        every { failedMapperDelegate.mapFailure<SearchResult>(expectedException) } returns Result.failure(expectedException)

        val result = searchedMapper.map(response)

        verify { failedMapperDelegate.mapFailure<SearchResult>(expectedException) }
        assertEquals(Result.failure<SearchResult>(expectedException), result)
    }

    @Test
    fun `test map when response body is null`() {
        val response = Response.success<SearchResultDTO>(null)
        val expectedException = CustomException(Error.NullObject)

        every { failedMapperDelegate.mapFailure<SearchResult>(expectedException) } returns Result.failure(expectedException)

        val result = searchedMapper.map(response)

        verify { failedMapperDelegate.mapFailure<SearchResult>(expectedException) }
        assertEquals(Result.failure<SearchResult>(expectedException), result)
    }

}
