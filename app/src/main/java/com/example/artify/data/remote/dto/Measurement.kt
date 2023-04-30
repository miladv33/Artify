package com.example.artify.data.remote.dto


data class Measurement(
    val elementDescription: Any,
    val elementMeasurements: ElementMeasurements,
    val elementName: String
)