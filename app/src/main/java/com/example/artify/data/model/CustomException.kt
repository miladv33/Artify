package com.example.artify.data.model

import com.example.artify.data.enum.Error

/**
 *
 * deal with any kinds of exceptions
 * @property error Error
 * @constructor
 */
data class CustomException(
    val error: Error
    ):Exception()