package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val apiService: ApiService
) {

    suspend fun getUsers(): Flow<DataState> = flow {
        emit(DataState.Loading)
        Log.i("Debug", "in repo thread is ${Thread.currentThread().name}")
        delay(10000)
        try {
            val users = apiService.getUsers()
            Log.i("Debug", "repo fetching data")
            emit(DataState.Success(users))
        } catch (e: Exception) {
            emit(DataState.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}