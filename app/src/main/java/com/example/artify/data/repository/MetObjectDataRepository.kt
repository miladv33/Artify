package com.example.artify.data.repository

import com.example.artify.data.map.mappers.ObjectDataMapper
import com.example.artify.data.model.MetObjectData
import com.example.artify.data.remote.MetService
import com.example.artify.data.repository.safecall.SafeCallDelegate
import com.example.artify.data.repository.safecall.SafeCallDelegateImpl
import javax.inject.Inject

class MetObjectDataRepository @Inject constructor(
    private val apiService: MetService,
    private val mapper: ObjectDataMapper,
    private val safeCallDelegateImpl: SafeCallDelegateImpl
) : Repository.MetObjectDataRepository, SafeCallDelegate by safeCallDelegateImpl {
    override suspend fun getMetObjectData(objectData: Int) = safeCall {
        val metObjectData = apiService.getObject(objectData)
        mapper.map(metObjectData)
    }
}