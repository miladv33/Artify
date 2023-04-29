package com.example.artify.presentation.loading

import androidx.lifecycle.LiveData

interface ILoading {
    fun showLoading()
    fun hideLoading()
    fun getLoadingLiveData(): LiveData<Boolean>
}