package com.peter.androidinterview.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.peter.androidinterview.R
import com.peter.androidinterview.domain.models.User

class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val nameTxtView: MaterialTextView = view.findViewById(R.id.name)
    private val emailTxtView: MaterialTextView = view.findViewById(R.id.email)
    private val detailsBtn: MaterialButton = view.findViewById(R.id.details)


    fun bindTo(user: User?, onItemClicked: (user: User) -> Unit){

        if(user != null){
            nameTxtView.text = user.name
            emailTxtView.text = user.email

            detailsBtn.setOnClickListener {
                onItemClicked.invoke(user)
            }
        }else{
            nameTxtView.text = ""
            emailTxtView.text = ""
        }
        absoluteAdapterPosition
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false)
            )
        }
    }
}