package com.example.mvvm_coroutinesapplication.Network

import com.example.mvvm_coroutinesapplication.Model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("users")
    suspend fun getPost(@Query("page") page: Int):Post
}