package com.peter.androidinterview.data.repo_interfaces

import com.peter.androidinterview.domain.models.*

/**
 * An interface for all methods to be implemented for the remote repo
 */
interface RemoteRepo {
    suspend fun fetchAllUsers(): List<User>

    suspend fun fetchPostsByUser(userId: Int): List<Post>

    suspend fun fetchCommentsByPost(postId: Int): List<Comment>

    suspend fun fetchAlbumsByUser(userId: Int): List<Album>

    suspend fun fetchPhotosByAlbum(albumId: Int): List<Photo>

}