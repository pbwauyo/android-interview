package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Album
import com.peter.androidinterview.domain.models.Post

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleTxtView: MaterialTextView = view.findViewById(R.id.title)
    private val bodyTxtView: MaterialTextView = view.findViewById(R.id.body)
    private val viewCommentsBtn: MaterialButton = view.findViewById(R.id.view_comments)

    fun bindTo(post: Post?, itemClicked: (post: Post) -> Unit){
        if(post != null){
            titleTxtView.text = post.title
            bodyTxtView.text = post.body
            viewCommentsBtn.setOnClickListener {
                itemClicked.invoke(post)
            }
        }else{
            titleTxtView.text = ""
            bodyTxtView.text = ""
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostViewHolder{
            return PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
            )
        }
    }
}