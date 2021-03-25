package com.peter.androidinterview.presentation.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.peter.androidinterview.domain.models.Album
import com.peter.androidinterview.presentation.adapters.PostsPagingAdapter.Companion.DIFF_CALL_BACK
import com.peter.androidinterview.presentation.viewholders.AlbumViewHolder

/**
 * Paging Adapter to be used with a RecyclerView.
 * The [DIFF_CALL_BACK] is used to perform diffing to ensure only changed items are updated
 */
class AlbumsPagingAdapter(private val progressIndicator: CircularProgressIndicator, private  val onItemClicked: (album: Album) -> Unit) :
    PagingDataAdapter<Album, AlbumViewHolder>(DIFF_CALL_BACK) {

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Album>(){
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        progressIndicator.visibility = View.GONE
        holder.bindTo(album, onItemClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder.create(parent)
    }
}