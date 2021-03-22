package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Comment

class CommentViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindTo(comment: Comment?){

    }

    companion object {
        fun create(parent: ViewGroup): CommentViewHolder{
            return CommentViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.comment_row, parent, false)
            )
        }
    }
}