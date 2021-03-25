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
class AppViewModel @Inject constructor(val remoteRepo: RemoteRepo, val api: Api) : ViewModel() {

    /**
     * Configuration for the [PagingSource]
     */
    private val pagingConfig = PagingConfig(
        pageSize = 30
    )

    private val _userIdLiveData = MutableLiveData<Int>()
    val userIdLiveData: LiveData<Int> get() = _userIdLiveData

    private val _postIdLiveData = MutableLiveData<Int>()
    val postIdLiveData: LiveData<Int> get() = _postIdLiveData

    private val _albumIdLiveData = MutableLiveData<Int>()
    val albumIdLiveData: LiveData<Int> get() = _albumIdLiveData

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
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    private var _postsLiveData = _userIdLiveData.switchMap {
        Pager(
            pagingConfig
        ){
            PostsPagingSource(api, it)
        }.liveData.cachedIn(viewModelScope)
    }
    val postsLiveData: LiveData<PagingData<Post>> get() = _postsLiveData

    //LiveData for albums
    /**
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    private var _albumsLiveData = _userIdLiveData.switchMap {
        Pager(
            pagingConfig
        ){
            AlbumsPagingSource(api, it)
        }.liveData.cachedIn(viewModelScope)
    }
    val albumsLiveData: LiveData<PagingData<Album>> get() = _albumsLiveData

    //LiveData for photos
    /**
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    private var _photosLiveData = _albumIdLiveData.switchMap {
        Pager(
            pagingConfig
        ){
            PhotosPagingSource(api, it)
        }.liveData.cachedIn(viewModelScope)
    }
    val photosLiveData: LiveData<PagingData<Photo>> get() = _photosLiveData

    //LiveData for comments
    /**
     * The [Pager] creates livedata by calling the load() method from the [PagingSource].
     * The [pagingConfig] configures the parameters for the [PagingSource]
     */
    private val _commentsLiveData = _postIdLiveData.switchMap {
        Pager(
            pagingConfig
        ){
            CommentsPagingSource(api, it)
        }.liveData.cachedIn(viewModelScope)
    }
    val commentsLiveData: LiveData<PagingData<Comment>> get() = _commentsLiveData

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