package com.example.mvvm_coroutinesapplication.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutinesapplication.Model.Post
import com.example.mvvm_coroutinesapplication.Repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository): ViewModel() {

    val postMutableLiveData:MutableLiveData<Post> = MutableLiveData()
    var page: Int = 0

    fun getPost(){
        viewModelScope.launch {
            try {
                val response = postRepository.getPost(page)
                postMutableLiveData.value = response
            }catch (exp:Exception){
               Log.e("Exception>>", exp.message!!)
            }
        }
    }
}