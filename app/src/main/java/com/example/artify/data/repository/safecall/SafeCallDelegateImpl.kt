package com.example.artify.data.repository.safecall

import com.example.artify.data.map.delegate.failedmap.FailedMapperDelegate

class SafeCallDelegateImpl(private val failedMapperDelegate: FailedMapperDelegate) : SafeCallDelegate,
    FailedMapperDelegate by failedMapperDelegate {
    override suspend fun <T> safeCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            call.invoke()
        } catch (e: Exception) {
            mapFailure(e)
        }
    }


}