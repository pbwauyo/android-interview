package com.peter.androidinterview.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.peter.androidinterview.domain.models.Post
import com.peter.androidinterview.presentation.viewholders.PostViewHolder

/**
 * Paging Adapter to be used with a RecyclerView.
 * The [DIFF_CALL_BACK] is used to perform diffing to ensure only changed items are updated
 */
class PostsPagingAdapter (private val progressIndicator: CircularProgressIndicator,
                          private val onItemClicked: (post: Post) -> Unit) :
    PagingDataAdapter<Post, PostViewHolder>(DIFF_CALL_BACK) {

    companion object {
        val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Post> (){
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        progressIndicator.visibility = View.GONE
        val post = getItem(position)
        holder.bindTo(post, onItemClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.create(parent)
    }

}