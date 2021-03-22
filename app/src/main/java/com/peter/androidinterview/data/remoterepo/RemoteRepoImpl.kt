package com.peter.androidinterview.data.remoterepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.data.repo_interfaces.RemoteRepo
import com.peter.androidinterview.domain.models.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * An implementation of the [RemoteRepo] interface.
 * It uses an instance of [Api] to fetch data.
 */
class RemoteRepoImpl : RemoteRepo{
    @Inject lateinit var api: Api

    private val disposables = CompositeDisposable()

    /**
     * Performs an api call to fetch all users.
     * Returns a list of users.
     */
    override suspend fun fetchAllUsers(): List<User> {
        return api.fetchAllUsers()
    }

    /**
     * Performs an api call to fetch posts by a specific [User].
     * Returns a list of posts.
     */
    override suspend fun fetchPostsByUser(userId: Int): List<Post> {
        return api.fetchPostsByUser(userId)
    }

    /**
     * Performs an api call to fetch all comments on a specific post.
     * Returns a list of [Comment].
     */
    override suspend fun fetchCommentsByPost(postId: Int): List<Comment> {
        return api.fetchCommentsByPost(postId)
    }

    /**
     * Performs an api call to fetch all albums by a specific user.
     * Returns a list of [Album].
     */
    override suspend fun fetchAlbumsByUser(userId: Int): List<Album> {
        return api.fetchAlbumsByUser(userId)
    }

    /**
     * Performs an api call to fetch all photos in a specific album.
     * Returns a list of [Photo].
     */
    override suspend fun fetchPhotosByAlbum(albumId: Int): List<Photo> {
        return fetchPhotosByAlbum(albumId)
    }

}