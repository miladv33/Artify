package com.example.artify.data.remote

import com.example.artify.data.remote.dto.MetObjectDataDTO
import com.example.artify.data.remote.dto.SearchResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetService {
    // Search for objects using a query term and an offset
    @GET("/public/collection/v1/search")
    suspend fun searchObjects(@Query("q") query: String?): Response<SearchResultDTO>?

    // Get the details of an object by its ID
    @GET("/public/collection/v1/objects/{id}")
    suspend fun getObject(@Path("id") id: Int): Response<MetObjectDataDTO?>?
}