package com.peter.androidinterview.presentation.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.peter.androidinterview.domain.models.Photo
import com.peter.androidinterview.presentation.adapters.PostsPagingAdapter.Companion.DIFF_CALL_BACK
import com.peter.androidinterview.presentation.viewholders.PhotoViewHolder

/**
 * Paging Adapter to be used with a RecyclerView.
 * The [DIFF_CALL_BACK] is used to perform diffing to ensure only changed items are updated
 */
class PhotosPagingAdapter : PagingDataAdapter<Photo, PhotoViewHolder>(DIFF_CALL_BACK) {

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Photo>(){
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bindTo(photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.create(parent)
    }
}