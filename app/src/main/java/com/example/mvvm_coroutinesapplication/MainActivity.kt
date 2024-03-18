package com.example.mvvm_coroutinesapplication

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_coroutinesapplication.Model.Post
import com.example.mvvm_coroutinesapplication.Repository.PostRepository
import com.example.mvvm_coroutinesapplication.ViewModel.PostViewModel
import com.example.mvvm_coroutinesapplication.ViewModel.PostViewModelFactory
import com.example.mvvm_coroutinesapplication.adapter.ListAdapter

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var noTextFound: TextView
    lateinit var postAdapter: ListAdapter
    lateinit var postViewModel: PostViewModel
    var pd : ProgressDialog? = null
    lateinit var post : Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        val postRepository = PostRepository()
        val viewModelFactory = PostViewModelFactory(postRepository)
        postViewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]
        postViewModel.getPost()
        pd = ProgressDialog(this@MainActivity)
        pd?.setTitle("Please wait...")
        pd?.setCancelable(false)
        pd?.show()
        postViewModel.postMutableLiveData.observe(this, Observer {
            pd?.dismiss()
            if(it.data.size != 0){
                noTextFound.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                postAdapter.setData(it as Post)
            }else{
                postViewModel.page = 0
                noTextFound.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }

        })
    }

    private fun initView() {
        post = Post()
        recyclerView = findViewById(R.id.id_list_rc_view)
        noTextFound = findViewById(R.id.id_no_data_found_txt)
        postAdapter = ListAdapter(this, post)

        recyclerView.apply {
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = postAdapter
        }
    }
}