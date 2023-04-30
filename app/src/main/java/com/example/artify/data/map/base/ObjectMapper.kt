package com.example.artify.data.map.base

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.artify.data.model.Model

interface ObjectMapper<DTO, T : Model> {
    val failedMapperDelegate: FailedMapperDelegate

    fun map(input: Result<DTO>): T? {
        return createModelFromDTO(input)
    }

    fun createModelFromDTO(input: Result<DTO>): T?
}