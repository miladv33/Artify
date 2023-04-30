package com.example.artify.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artify.data.model.MetObjectData
import com.example.artify.domain.usecase.metobject.MetObjectUseCase
import com.example.artify.presentation.error.ShowErrorDelegate
import com.example.artify.presentation.loading.ILoading
import com.example.artify.presentation.loading.LoadingDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val metObjectUseCase: MetObjectUseCase,
    private val loading: LoadingDelegate,
    private val showErrorDelegate: ShowErrorDelegate
) : ViewModel(), ILoading by loading,
    ShowErrorDelegate by showErrorDelegate {
    private val _metObjectResult: MutableLiveData<MetObjectData> = MutableLiveData()
    val metObjectResult: LiveData<MetObjectData> = _metObjectResult
    fun getObject(objectId: Int) {
        showLoading()
        viewModelScope.launch {
            metObjectUseCase.getMetObjectData(objectId)
                .flowOn(Dispatchers.IO)
                .collect {
                    hideLoading()
                    it.onSuccess {
                        _metObjectResult.value = it
                    }
                    it.onFailure { throwable ->
                        onFailure(throwable)
                    }
                }
        }
    }

}