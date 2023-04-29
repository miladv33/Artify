package com.example.artify.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artify.data.paging.IPageManger
import com.example.artify.data.paging.PageManger
import com.example.artify.domain.usecase.SearchUserCase
import com.example.artify.model.base.SearchResult
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

    fun search(query: String) {
        if (thereIsCachedData(query)) {
            getCashedata()
        } else {
            viewModelScope.launch {
                searchUserCase.search(query = query).flowOn(Dispatchers.IO).collect {
                    it.onSuccess { searchResult ->
                        handleSearchSuccess(query, searchResult)
                    }
                    it.onFailure {
                        it
                    }
                }
            }
        }
    }

    private fun handleSearchSuccess(query: String, searchResult: SearchResult) {
        setCachedData(
            query,
            searchResult.objectIDs
        )
        getCashedata()
    }

    private fun getCashedata() {
        val newList = ArrayList<Int>() // create a new list
        if (_searchResult.value.isNullOrEmpty()) {
            newList.addAll(
                getCachedDataFirstPage() ?: emptyList()
            ) // add the first page to the new list
        } else {
            newList.addAll(
                _searchResult.value ?: emptyList()
            ) // add the current value to the new list
            newList.addAll(
                getCachedDataFirstPage() ?: emptyList()
            ) // add the next page to the new list
        }
        _searchResult.value = newList // assign the new list to the _searchResult.value
    }
}