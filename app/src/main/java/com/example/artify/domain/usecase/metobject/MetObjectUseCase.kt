package com.example.artify.domain.usecase.metobject

import com.example.artify.data.model.MetObjectData
import com.example.artify.data.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface MetObjectUseCase {
    fun getMetObjectData(metObjectId: Int): Flow<Result<MetObjectData>>
}