package com.example.mvvm_coroutinesapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_coroutinesapplication.Model.Post
import com.example.mvvm_coroutinesapplication.R
import com.squareup.picasso.Picasso

class ListAdapter(private val context: Context, private var postList:Post) : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false))
    }

    override fun getItemCount(): Int = postList.data.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val post = postList.data

        Picasso.get()
            .load(post[position].avatar)
            .into(holder.avatar);

        holder.id.text = post[position].id.toString()
        holder.name.text = post[position].first_name +" "+ post[position].last_name
        holder.email.text = post[position].email
    }

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.id_image_avatar)
        val id: TextView = itemView.findViewById(R.id.id_textView)
        val name: TextView = itemView.findViewById(R.id.id_title_textView)
        val email: TextView = itemView.findViewById(R.id.id_description_textView)
    }

    fun setData(post: Post){
        this.postList = post
        notifyDataSetChanged()
    }

}

