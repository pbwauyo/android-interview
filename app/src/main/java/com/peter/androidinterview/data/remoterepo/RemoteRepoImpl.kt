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
class RemoteRepoImpl @Inject constructor(val api: Api) : RemoteRepo{

    /**
     * Performs an api call to fetch all users.
     * Returns a list of users.
     */
    override suspend fun fetchAllUsers(): List<User> {
        return api.fetchAllUsers()
    }

}