package com.danielpasser.newsapp.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielpasser.newsapp.model.Article
import com.danielpasser.newsapp.repository.Repository
import com.danielpasser.newsapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Article>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Article>>> get() = _dataState

    fun downLoadArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNews().onEach { dateState -> _dataState.value = dateState }
                .launchIn(viewModelScope)
        }
    }
}