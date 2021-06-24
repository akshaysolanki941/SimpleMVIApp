package com.example.myapplication.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.Repository
import com.example.myapplication.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {

    private val _dataState = MutableLiveData<DataState>()

    val dataState: LiveData<DataState>
        get() = _dataState

    init {
        Log.i("Debug", "viewmodel init")
        viewModelScope.launch {
            Log.i("Debug", "in viewmodel thread is ${Thread.currentThread().name}")
            repository.getUsers().collect { dataState ->
                _dataState.value = dataState
            }
        }
    }
}