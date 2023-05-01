package com.example.artify.presentation.error

import androidx.lifecycle.LiveData

interface ShowErrorDelegate {
    fun onFailure(throwable: Throwable)
    fun showDialog()

    fun hideDialog()

    fun getErrorDialogState(): LiveData<Boolean>

    fun getErrorMessage(): String

    fun isErrorState(): Boolean

    fun setErrorState(boolean: Boolean)
}