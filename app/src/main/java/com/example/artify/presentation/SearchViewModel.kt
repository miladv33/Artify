package com.example.artify.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artify.data.remote.dto.SearchResultDTO
import com.example.artify.domain.usecase.SearchUserCase
import com.example.artify.model.base.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUserCase: SearchUserCase) :
    ViewModel() {
    private var _searchResult: MutableLiveData<SearchResult> = MutableLiveData()
     val searchResult: LiveData<SearchResult> = _searchResult
    fun search(query: String)  {
        viewModelScope.launch {
            searchUserCase.search(query).flowOn(Dispatchers.IO)
                .collect {
                    it.onSuccess {
                        _searchResult.value = it
                    }
                    it.onFailure {

                    }
                }
        }
    }

    fun clearResult() {
//        _searchResult = MutableLiveData<PagingData<Int>>()
    }
}