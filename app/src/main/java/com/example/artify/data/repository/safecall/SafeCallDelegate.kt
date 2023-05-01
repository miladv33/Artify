package com.example.artify.data.repository.safecall

interface SafeCallDelegate {
    suspend fun <T>safeCall(call: suspend () -> Result<T>): Result<T>
}