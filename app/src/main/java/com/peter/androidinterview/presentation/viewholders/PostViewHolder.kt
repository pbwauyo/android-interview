package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Post

class PostViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(post: Post?){

    }

    companion object {
        fun create(parent: ViewGroup): PostViewHolder{
            return PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
            )
        }
    }
}