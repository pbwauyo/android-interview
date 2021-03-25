package com.peter.androidinterview.presentation.viewmodels

import androidx.lifecycle.*
import androidx.paging.*
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.data.pagingsource.AlbumsPagingSource
import com.peter.androidinterview.data.pagingsource.CommentsPagingSource
import com.peter.androidinterview.data.pagingsource.PhotosPagingSource
import com.peter.androidinterview.data.pagingsource.PostsPagingSource
import com.peter.androidinterview.data.repo_interfaces.RemoteRepo
import com.peter.androidinterview.domain.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val remoteRepo: RemoteRepo) : ViewModel() {

    private val _userIdLiveData = MutableLiveData<Int>()

    private val _postIdLiveData = MutableLiveData<Int>()

    private val _albumIdLiveData = MutableLiveData<Int>()

    //LiveData for users
    /**
     * All users are fetched from here. There's no pagination here since the users are few(10)
     */
    private var _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData
    init {
        viewModelScope.launch {
            _usersLiveData.value = remoteRepo.fetchAllUsers()
        }
    }

    //LiveData for posts
    /**
     * Fetch a user's posts when the [_userIdLiveData]'s value changes
     */
    private var _postsLiveData = _userIdLiveData.switchMap {
        remoteRepo.fetchPostsByUser(it).cachedIn(viewModelScope)
    }
    val postsLiveData: LiveData<PagingData<Post>> get() = _postsLiveData

    //LiveData for albums
    /**
     * Fetch a user's albums when [_userIdLiveData]'s value changes
     */
    private var _albumsLiveData = _userIdLiveData.switchMap {
        remoteRepo.fetchAlbumsByUser(it).cachedIn(viewModelScope)
    }
    val albumsLiveData: LiveData<PagingData<Album>> get() = _albumsLiveData

    //LiveData for photos
    /**
     * Fetch the photos in an album when the [_albumIdLiveData]'s value changes
     */
    private var _photosLiveData = _albumIdLiveData.switchMap {
        remoteRepo.fetchPhotosByUser(it).cachedIn(viewModelScope)
    }
    val photosLiveData: LiveData<PagingData<Photo>> get() = _photosLiveData

    //LiveData for comments
    /**
     * Fetch the comments on a post when the [_postIdLiveData]'s value changes
     */
    private val _commentsLiveData = _postIdLiveData.switchMap {
        remoteRepo.fetchCommentsByPost(it).cachedIn(viewModelScope)
    }
    val commentsLiveData: LiveData<PagingData<Comment>> get() = _commentsLiveData

    /**
     * Updates the [_userIdLiveData] value only if there's a differing change
     */
    fun updateUserId(newUserId: Int){
        if(newUserId != _userIdLiveData.value){
            _userIdLiveData.value = newUserId
        }
    }

    /**
     * Updates the [_postIdLiveData] value only if there's a differing change
     */
    fun updatePostId(newPostId: Int){
        if(newPostId != _postIdLiveData.value){
            _postIdLiveData.value = newPostId
        }
    }

    /**
     * Updates the [_albumIdLiveData] value only if there's a differing change
     */
    fun updateAlbumId(newAlbumId: Int){
        if(newAlbumId != _albumIdLiveData.value){
            _albumIdLiveData.value = newAlbumId
        }
    }

}