package com.peter.androidinterview.apis

import com.peter.androidinterview.domain.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Api interface for performing several network requests.
 */
interface Api {
    /**
     * Fetch all users.
     * Returns a list of [User].
     */
    @GET("users")
    suspend fun  fetchAllUsers(): List<User>

    /**
     * Fetch all posts by a specific user identified by the [userId] param.
     * Returns a list of [Post]
     */
    @GET("posts")
    suspend fun fetchPostsByUser(@Query("userId") userId: Int, @Query("_start") start: Int, @Query("_limit") limit: Int): List<Post>

    /**
     * Add New [Post].
     * Return added Post as response.
     * NOTE: [Post] will only be faked as added.
     */
    @POST("users")
    suspend fun addNewPost(@Body post: Post): Post

    /**
     * Fetch all comments on a specific post identified by the [postId] param
     * Returns a list of [Comment]
     */
    @GET("comments")
    suspend fun fetchCommentsByPost(@Query("postId") postId: Int, @Query("_start") start: Int, @Query("_limit") limit: Int): List<Comment>

    /**
     * Fetch all albums of a specific user identified by the [userId] param.
     * Returns a list of [Album]
     */
    @GET("albums")
    suspend fun fetchAlbumsByUser(@Query("userId") userId: Int, @Query("_start") start: Int, @Query("_limit") limit: Int): List<Album>

    /**
     * Fetch all photos in a specific album identified by the [albumId] param.
     * Returns a list of [Photo]
     */
    @GET("photos")
    suspend fun fetchPhotosByAlbum(@Query("albumId") albumId: Int, @Query("_start") start: Int, @Query("_limit") limit: Int): List<Photo>
}