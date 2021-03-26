package com.peter.androidinterview.data.repo_interfaces

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.peter.androidinterview.domain.models.*

/**
 * An interface for all methods to be implemented for the remote repo
 */
interface RemoteRepo {

    suspend fun fetchAllUsers(): List<User>

    fun fetchPostsByUser(userId: Int): LiveData<PagingData<Post>>

    fun fetchAlbumsByUser(userId: Int): LiveData<PagingData<Album>>

    fun fetchPhotosByUser(albumId: Int): LiveData<PagingData<Photo>>

    fun fetchCommentsByPost(postId: Int): LiveData<PagingData<Comment>>

    suspend fun postNewPost(post: Post): Post

}