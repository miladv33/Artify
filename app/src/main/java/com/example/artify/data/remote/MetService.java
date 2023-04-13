package com.example.artify.data.remote;

import android.app.appsearch.SearchResult;

import com.example.artify.data.dto.MetObjectDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetService {

    // Search for objects using a query term and an offset
    @GET("/public/collection/v1/search")
    Call<SearchResult> searchObjects(@Query("q") String query);

    // Get the details of an object by its ID
    @GET("/public/collection/v1/objects/{id}")
    Call<MetObjectDTO> getObject(@Path("id") int id);
}