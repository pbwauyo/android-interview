package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Album

class AlbumViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(album: Album?){

    }

    companion object {
        fun create(parent: ViewGroup): AlbumViewHolder{
            return AlbumViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.album_row, parent, false)
            )
        }
    }
}