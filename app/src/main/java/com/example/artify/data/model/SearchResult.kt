package com.example.artify.data.model

data class SearchResult(
    var objectIDs: List<Int>,
    var hasMoreData:Boolean = true
) : Model()