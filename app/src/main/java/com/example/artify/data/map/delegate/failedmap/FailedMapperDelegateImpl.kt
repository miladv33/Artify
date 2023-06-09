package com.example.artify.data.map.delegate.failedmap

class FailedMapperDelegateImpl: FailedMapperDelegate {
    override fun <T>mapFailure(exception: Exception): Result<T> = Result.failure(exception)
}