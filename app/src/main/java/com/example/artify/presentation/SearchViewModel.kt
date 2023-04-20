package com.example.artify.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.artify.data.paging.SearchQuery
import com.example.artify.domain.usecase.SearchUserCase
import com.example.artify.model.base.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUserCase: SearchUserCase) :
    ViewModel() {
    private val _searchResult: MutableLiveData<PagingData<Int>> = MutableLiveData()
     val searchResult: LiveData<PagingData<Int>> = _searchResult
    fun search(query: String)  {
        viewModelScope.launch {
            searchUserCase.search(query).flowOn(Dispatchers.IO)
                .collect {
                    _searchResult.value = it
                }
        }
    }
}