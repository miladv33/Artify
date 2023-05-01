package com.example.artify.data.map.mappers

import com.example.artify.data.map.base.ResponseMapper
import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.artify.data.model.MetObjectData
import com.example.artify.data.remote.dto.MetObjectDataDTO
import retrofit2.Response
import javax.inject.Inject

class ObjectDataMapper @Inject constructor(
    override val failedMapperDelegate: FailedMapperDelegateImpl
) : ResponseMapper<MetObjectDataDTO, MetObjectData> {
    override fun createModelFromDTO(input: Response<MetObjectDataDTO>): MetObjectData {
        val dto = input.body()!!
        return MetObjectData(
            objectId = dto.objectID,
            title = dto.title,
            artistDisplayName = dto.artistDisplayName,
            objectDate = dto.objectDate,
            department = dto.department,
            objectName = dto.objectName,
            primaryImage = dto.primaryImage,
            additionalImages = dto.additionalImages
        )
    }
}