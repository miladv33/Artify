package com.example.artify.data.model


data class MetObjectData(
    val objectId: Int,
    val title: String,
    val artistDisplayName: String,
    val objectDate: String,
    val department: String,
    val objectName: String,
    val primaryImage: String,
    val additionalImages: List<String>
):Model()