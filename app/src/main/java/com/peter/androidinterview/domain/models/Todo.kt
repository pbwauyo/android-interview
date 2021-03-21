package com.peter.androidinterview.domain.models

data class Todo (
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
    )