package com.example.artify.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artify.data.model.SearchResult
import com.example.artify.data.paging.IPageManger
import com.example.artify.data.paging.PageManger
import com.example.artify.domain.usecase.search.SearchUserCase
import com.example.artify.presentation.error.ShowErrorDelegate
import com.example.artify.presentation.loading.ILoading
import com.example.artify.presentation.loading.LoadingDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserCase: SearchUserCase,
    private val loading: LoadingDelegate,
    private val showErrorDelegate: ShowErrorDelegate
) :
    ViewModel(), ILoading by loading,
    ShowErrorDelegate by showErrorDelegate {
    private var _searchResult: MutableLiveData<ArrayList<Int>> = MutableLiveData(ArrayList())
    val searchResult: LiveData<ArrayList<Int>> = _searchResult
    private var objectList = ArrayList<Int>()
    private val _hasMoreData = MutableLiveData<Boolean>()
    val hasMoreData: LiveData<Boolean> = _hasMoreData
    var lastSearchQuery = ""
        private set(value) {
            field = value
        }

    fun search(query: String) {
        performSearch(query.trimEnd())
    }

    private fun performSearch(query: String) {
        lastSearchQuery = query
        showLoading()
        viewModelScope.launch {
            searchUserCase.search(query = query).flowOn(Dispatchers.IO).collect {
                hideLoading()
                it.onSuccess { searchResult ->
                    handleSuccessResult(searchResult)
                }
                it.onFailure { throwable ->
                    onFailure(throwable)
                }
            }
        }
    }

    private fun handleSuccessResult(searchResult: SearchResult) {
        setHasMore(searchResult.hasMoreData)
        addItems(searchResult)
    }

    private fun setHasMore(hasMoreData: Boolean) {
        _hasMoreData.value = hasMoreData
    }

    private fun addItems(searchResult: SearchResult) {
        val newList = ArrayList<Int>()
        newList.addAll(objectList)
        newList.addAll(searchResult.objectIDs)
        objectList = newList
        _searchResult.value = newList
    }

    fun clearResults() {
        _hasMoreData.value = false
        objectList.clear()
        _searchResult.value = ArrayList()
    }
}