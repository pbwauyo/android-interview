package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.User

class UserViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(user: User?){

    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false)
            )
        }
    }
}