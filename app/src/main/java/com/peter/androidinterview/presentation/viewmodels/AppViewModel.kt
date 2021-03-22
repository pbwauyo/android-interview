package com.peter.androidinterview.presentation.viewmodels

import androidx.lifecycle.*
import com.peter.androidinterview.data.repo_interfaces.RemoteRepo
import com.peter.androidinterview.domain.models.Album
import com.peter.androidinterview.domain.models.Photo
import com.peter.androidinterview.domain.models.Post
import com.peter.androidinterview.domain.models.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppViewModel : ViewModel() {
    @Inject lateinit var remoteRepo: RemoteRepo

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
    val postsLiveData: LiveData<List<Post>> get() = _postsLiveData

    private fun fetchPostsByUser(userId: Int): MutableLiveData<List<Post>>{
        val postsLiveData = MutableLiveData<List<Post>>()
        viewModelScope.launch {
            postsLiveData.value = remoteRepo.fetchPostsByUser(userId)
        }
        return postsLiveData
    }

    //LiveData for albums
    private var _albumsLiveData = MutableLiveData<List<Album>>()
    val albumsLiveData = _userIdLiveData.switchMap {
        fetchAlbumsByUser(it)
    }

    private fun fetchAlbumsByUser(userId: Int): MutableLiveData<List<Album>>{
        val albumsLiveData = MutableLiveData<List<Album>>()
        viewModelScope.launch {
            albumsLiveData.value = remoteRepo.fetchAlbumsByUser(userId)
        }
        return albumsLiveData
    }

    //LiveData for photos
    private var _photosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData = _userIdLiveData.switchMap {
        fetchPhotosByAlbum(it)
    }

    private fun fetchPhotosByAlbum(albumId: Int): MutableLiveData<List<Photo>>{
        val albumsLiveData = MutableLiveData<List<Photo>>()
        viewModelScope.launch {
            albumsLiveData.value = remoteRepo.fetchPhotosByAlbum(albumId)
        }
        return albumsLiveData
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