package com.example.artify.presentation.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class LoadingDelegate : ILoading {
    private val _loadingData: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun showLoading() {
        _loadingData.value = true
    }

    override fun hideLoading() {
        _loadingData.value = false
    }

    override fun getLoadingLiveData(): LiveData<Boolean> {
        return _loadingData
    }
}