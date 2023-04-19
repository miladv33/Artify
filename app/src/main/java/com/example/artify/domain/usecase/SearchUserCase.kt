package com.example.artify.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SearchUserCase {
    fun search(query: String): Flow<PagingData<Int>>
}