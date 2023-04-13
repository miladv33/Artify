package com.example.artify.data.remote.dto

data class ConstituentDTO(
    val constituentID: Int,
    val constituentULAN_URL: String,
    val constituentWikidata_URL: String,
    val gender: String,
    val name: String,
    val role: String
)