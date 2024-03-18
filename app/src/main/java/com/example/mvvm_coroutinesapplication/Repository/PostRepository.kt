package com.example.mvvm_coroutinesapplication.Repository

import com.example.mvvm_coroutinesapplication.Model.Post
import com.example.mvvm_coroutinesapplication.Network.RetrofitBuilder

class PostRepository {

    suspend fun getPost(page : Int):Post = RetrofitBuilder.api.getPost(page)

}