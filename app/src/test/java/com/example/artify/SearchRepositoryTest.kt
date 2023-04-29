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

}
