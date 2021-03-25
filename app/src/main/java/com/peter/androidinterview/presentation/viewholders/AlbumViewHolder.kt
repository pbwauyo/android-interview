package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Album

class AlbumViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    private val titleTxtView: MaterialTextView = view.findViewById(R.id.title)
    private val viewPhotosBtn: MaterialButton = view.findViewById(R.id.view_photos)

    fun bindTo(album: Album?, itemClicked: (album: Album) -> Unit){
        if(album != null){
            titleTxtView.text = album.title
            viewPhotosBtn.setOnClickListener {
                itemClicked.invoke(album)
            }
        }else{
            titleTxtView.text = ""
        }

    }

    companion object {
        fun create(parent: ViewGroup): AlbumViewHolder{
            return AlbumViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.album_row, parent, false)
            )
        }
    }
}