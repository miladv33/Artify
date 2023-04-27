package com.example.artify.data.map.base

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.artify.model.base.CustomException
import com.example.artify.model.base.Model
import retrofit2.Response
import com.example.artify.data.enum.Error

interface ObjectMapper<DTO, T : Model> {
    val failedMapperDelegate: FailedMapperDelegate

    fun map(input: Result<DTO>): T? {
        return createModelFromDTO(input)
    }

    fun createModelFromDTO(input: Result<DTO>): T?
}