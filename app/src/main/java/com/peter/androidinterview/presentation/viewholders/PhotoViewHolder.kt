package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.Photo

class PhotoViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    private val imageImgView: ImageView = view.findViewById(R.id.image)
    private val titleTxtView: MaterialTextView = view.findViewById(R.id.title)

    fun bindTo(photo: Photo?){
        if(photo != null){

            Glide.with(view.context)
                .load("${photo.thumbnailUrl}.png")
                .centerCrop()
                .into(imageImgView)

            titleTxtView.text = photo.title
        }else{
            titleTxtView.text = ""
        }
    }

    companion object{
        /**
         * Create a [PhotoViewHolder] instance from [ViewGroup]
         */
        fun create(parent: ViewGroup): PhotoViewHolder{
            return PhotoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
            )
        }
    }
}