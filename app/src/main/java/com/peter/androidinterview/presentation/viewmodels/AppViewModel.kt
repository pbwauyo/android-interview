package com.peter.androidinterview.presentation.viewmodels

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.data.pagingsource.AlbumsPagingSource
import com.peter.androidinterview.data.pagingsource.CommentsPagingSource
import com.peter.androidinterview.data.pagingsource.PhotosPagingSource
import com.peter.androidinterview.data.pagingsource.PostsPagingSource
import com.peter.androidinterview.data.repo_interfaces.RemoteRepo
import com.peter.androidinterview.domain.models.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppViewModel : ViewModel() {
    @Inject lateinit var remoteRepo: RemoteRepo
    @Inject lateinit var api: Api

    val pagingConfig = PagingConfig(
        pageSize = 30
    )

    private val _userIdLiveData = MutableLiveData<Int>()
    /**
     * Exposed [userIdLiveData] which cannot be edited directly
     */
    val userIdLiveData: LiveData<Int> get() = _userIdLiveData

    private val _postIdLiveData = MutableLiveData<Int>()
    /**
     * Exposed [postIdLiveData] which cannot be edited directly
     */
    val postIdLiveData: LiveData<Int> get() = _postIdLiveData

    private val _albumIdLiveData = MutableLiveData<Int>()
    /**
     * Exposed [albumIdLiveData] livedata which cannot be edited directly
     */
    val albumIdLiveData: LiveData<Int> get() = _albumIdLiveData

    //LiveData for users
    private var _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData
    init {
        viewModelScope.launch {
            _usersLiveData.value = remoteRepo.fetchAllUsers()
        }
    }


    //LiveData for posts
    private var _postsLiveData = _userIdLiveData.switchMap {
        fetchPostsByUser(it)
    }
    val postsLiveData: LiveData<PagingData<Post>> get() = _postsLiveData

    private fun fetchPostsByUser(userId: Int): MutableLiveData<PagingData<Post>>{
        val postsLiveData = MutableLiveData<PagingData<Post>>()
        val postsPagingLiveData = Pager(
            pagingConfig
        ){
            PostsPagingSource(api, userId)
        }.liveData
        postsLiveData.value = postsPagingLiveData.value
        return postsLiveData
    }

    //LiveData for albums
    private var _albumsLiveData = _userIdLiveData.switchMap {
        fetchAlbumsByUser(it)
    }
    val albumsLiveData: LiveData<PagingData<Album>> get() = _albumsLiveData

    private fun fetchAlbumsByUser(userId: Int): MutableLiveData<PagingData<Album>>{
        val albumsLiveData = MutableLiveData<PagingData<Album>>()
        val albumsPagingLiveData = Pager(
            pagingConfig
        ){
            AlbumsPagingSource(api, userId)
        }.liveData
        albumsLiveData.value = albumsPagingLiveData.value
        return albumsLiveData
    }

    //LiveData for photos
    private var _photosLiveData = _userIdLiveData.switchMap {
        fetchPhotosByAlbum(it)
    }
    val photosLiveData: LiveData<PagingData<Photo>> get() = _photosLiveData

    private fun fetchPhotosByAlbum(albumId: Int): MutableLiveData<PagingData<Photo>>{
        val photosLiveData = MutableLiveData<PagingData<Photo>>()
        val photosPagingLiveData = Pager(
            pagingConfig
        ){
            PhotosPagingSource(api, albumId)
        }.liveData

        photosLiveData.value = photosPagingLiveData.value
        return photosLiveData
    }

    //LiveData for comments
    private val _commentsLiveData = _postIdLiveData.switchMap {
        fetchCommentsByPost(it)
    }
    val commentsLiveData: LiveData<PagingData<Comment>> get() = _commentsLiveData

    private fun fetchCommentsByPost(postId: Int): MutableLiveData<PagingData<Comment>>{
        val commentsLiveData = MutableLiveData<PagingData<Comment>>()
        val commentsPagingLiveData = Pager(
            pagingConfig
        ){
            CommentsPagingSource(api, postId)
        }.liveData
        commentsLiveData.value = commentsPagingLiveData.value
        return commentsLiveData
    }

    /**
     * Updates the [userIdLiveData] value only if there's a differing change
     */
    fun updateUserId(newUserId: Int){
        if(newUserId != _userIdLiveData.value){
            _userIdLiveData.value = newUserId
        }
    }

    /**
     * Updates the [postIdLiveData] value only if there's a differing change
     */
    fun updatePostId(newPostId: Int){
        if(newPostId != _postIdLiveData.value){
            _postIdLiveData.value = newPostId
        }
    }

    /**
     * Updates the [albumIdLiveData] value only if there's a differing change
     */
    fun updateAlbumId(newAlbumId: Int){
        if(newAlbumId != _albumIdLiveData.value){
            _albumIdLiveData.value = newAlbumId
        }
    }

}