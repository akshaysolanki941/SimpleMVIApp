package com.example.myapplication.retrofit

import com.example.myapplication.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers() : List<User>
}