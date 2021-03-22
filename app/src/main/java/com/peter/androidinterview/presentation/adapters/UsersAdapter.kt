package com.peter.androidinterview.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.androidinterview.domain.models.User
import com.peter.androidinterview.presentation.viewholders.UserViewHolder

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private val usersList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = usersList[position]
        holder.bindTo(user)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun submitList(list: ArrayList<User>){
        usersList - list
    }
}