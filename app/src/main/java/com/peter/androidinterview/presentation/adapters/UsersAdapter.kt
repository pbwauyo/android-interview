package com.peter.androidinterview.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.peter.androidinterview.domain.models.User
import com.peter.androidinterview.presentation.viewholders.UserViewHolder

class UsersAdapter (private val progressIndicator: CircularProgressIndicator,
                    private val onItemClicked: (user: User) -> Unit) :
    RecyclerView.Adapter<UserViewHolder>() {

    private var usersList: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        progressIndicator.visibility = View.GONE
        val user = usersList[position]
        holder.bindTo(user, onItemClicked)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun submitList(list: List<User>){
        usersList = list
        notifyDataSetChanged()
    }
}