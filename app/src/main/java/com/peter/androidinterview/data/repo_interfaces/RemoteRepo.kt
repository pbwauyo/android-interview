package com.peter.androidinterview.data.repo_interfaces

import com.peter.androidinterview.domain.models.*

/**
 * An interface for all methods to be implemented for the remote repo
 */
interface RemoteRepo {
    suspend fun fetchAllUsers(): List<User>

}