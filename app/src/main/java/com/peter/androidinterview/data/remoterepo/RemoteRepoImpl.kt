package com.peter.androidinterview.data.remoterepo

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.data.pagingsource.AlbumsPagingSource
import com.peter.androidinterview.data.pagingsource.CommentsPagingSource
import com.peter.androidinterview.data.pagingsource.PhotosPagingSource
import com.peter.androidinterview.data.pagingsource.PostsPagingSource
import com.peter.androidinterview.data.repo_interfaces.RemoteRepo
import com.peter.androidinterview.domain.models.*
import javax.inject.Inject

/**
 * An implementation of the [RemoteRepo] interface.
 * It uses an instance of [Api] to fetch data.
 */
class RemoteRepoImpl @Inject constructor(val api: Api) : RemoteRepo{

    /**
     * Configuration for the [PagingSource]
     */
    private val pagingConfig = PagingConfig(
        pageSize = 30
    )

    /**
     * Performs an api call to fetch all users.
     * Returns a list of users.
     */
    override suspend fun fetchAllUsers(): List<User> {
        return api.fetchAllUsers()
    }

    /**
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    override fun fetchPostsByUser(userId: Int): LiveData<PagingData<Post>> {
        return Pager(
            pagingConfig
        ){
            PostsPagingSource(api, userId)
        }.liveData
    }

    /**
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    override fun fetchAlbumsByUser(userId: Int): LiveData<PagingData<Album>> {
        return Pager(
            pagingConfig
        ){
            AlbumsPagingSource(api, userId)
        }.liveData
    }

    /**
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    override fun fetchPhotosByUser(albumId: Int): LiveData<PagingData<Photo>> {
        return Pager(
            pagingConfig
        ){
            PhotosPagingSource(api, albumId)
        }.liveData
    }

    /**
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    override fun fetchCommentsByPost(postId: Int): LiveData<PagingData<Comment>> {
        return Pager(
            pagingConfig
        ){
            CommentsPagingSource(api, postId)
        }.liveData
    }

    /**
     * Add New Post
     */
    override suspend fun postNewPost(post: Post): Post {
        return api.addNewPost(post)
    }


}