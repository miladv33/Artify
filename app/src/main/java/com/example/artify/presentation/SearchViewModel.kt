package com.example.artify.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artify.data.paging.IPageManger
import com.example.artify.data.paging.PageManger
import com.example.artify.domain.usecase.SearchUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserCase: SearchUserCase,
    private val pageManger: PageManger
) :
    ViewModel(), IPageManger<List<Int>> by pageManger {
    private var _searchResult: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    val searchResult: MutableLiveData<ArrayList<Int>> = _searchResult
    private val _loadingData: MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingData: LiveData<Boolean> = _loadingData

    fun search(query: String) {
        if (thereIsCachedData(query.trimEnd())) {
            getCashedData(_searchResult)
        } else {
            _loadingData.value = true
            clearResults()
            viewModelScope.launch {
                searchUserCase.search(query = query).flowOn(Dispatchers.IO).collect {
                    _loadingData.value = false
                    it.onSuccess { searchResult ->
                        handleSearchSuccess(query, searchResult, _searchResult)
                    }
                    it.onFailure {
                        it
                    }
                }
            }
        }
    }

    private fun clearResults() {
        _searchResult.value = ArrayList()
    }
}