package com.peter.androidinterview.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.peter.androidinterview.domain.models.Comment
import com.peter.androidinterview.presentation.adapters.PostsPagingAdapter.Companion.DIFF_CALL_BACK
import com.peter.androidinterview.presentation.viewholders.CommentViewHolder

/**
 * Paging Adapter to be used with a RecyclerView.
 * The [DIFF_CALL_BACK] is used to perform diffing to ensure only changed items are updated
 */
class CommentsPagingAdapter (private val progressIndicator: CircularProgressIndicator) :
    PagingDataAdapter<Comment, CommentViewHolder>(DIFF_CALL_BACK) {

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Comment>(){
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        progressIndicator.visibility = View.GONE
        val comment = getItem(position)
        holder.bindTo(comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.create(parent)
    }

}