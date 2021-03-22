package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Photo

class PhotoViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindTo(photo: Photo?){

    }

    companion object{
        fun create(parent: ViewGroup): PhotoViewHolder{
            return PhotoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
            )
        }
    }
}