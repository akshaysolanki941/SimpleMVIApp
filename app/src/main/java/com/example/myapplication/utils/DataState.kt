package com.example.myapplication.utils

import com.example.myapplication.model.User

sealed class DataState {
    object Loading : DataState()
    data class Success(val users: List<User>) : DataState()
    data class Error(val error: String) : DataState()
}