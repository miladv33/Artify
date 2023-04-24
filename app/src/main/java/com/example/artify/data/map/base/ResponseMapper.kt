package com.example.artify.data.map.base

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.artify.model.base.CustomException
import com.example.artify.model.base.Model
import retrofit2.Response
import com.example.artify.data.enum.Error

interface ResponseMapper<DTO, T : Model> {
    val failedMapperDelegate: FailedMapperDelegate

    fun map(input: Response<DTO>?): Result<T> {
        return input?.body()?.let { createModelFromDTO(input) }
            ?.let {
                Result.success(it)
            }
            ?:
            failedMapperDelegate.mapFailure(CustomException(Error.NullObject))
    }

    fun createModelFromDTO(input: Response<DTO>): T
}