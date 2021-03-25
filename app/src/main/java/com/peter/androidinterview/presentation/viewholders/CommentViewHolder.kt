package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Comment

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val emailTxtView: MaterialTextView = itemView.findViewById(R.id.email)
    private val nameTxtView: MaterialTextView = itemView.findViewById(R.id.name)
    private val bodyTxtView: MaterialTextView = itemView.findViewById(R.id.body)

    fun bindTo(comment: Comment?){
        if(comment != null){
            emailTxtView.text = comment.email
            nameTxtView.text = comment.name
            bodyTxtView.text = comment.body
        }else {
            emailTxtView.text = ""
            nameTxtView.text = ""
            bodyTxtView.text = ""
        }

    }

    companion object {
        fun create(parent: ViewGroup): CommentViewHolder{
            return CommentViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.comment_row, parent, false)
            )
        }
    }
}