package com.peter.androidinterview.di.modules

import com.peter.androidinterview.data.remoterepo.RemoteRepoImpl
import com.peter.androidinterview.data.repo_interfaces.RemoteRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module where repos are provided
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    /**
     * Provide an implementation of the [RemoteRepo] interface.
     */
    @Binds
    abstract fun bindRemoteRepo(remoteRepoImpl: RemoteRepoImpl): RemoteRepo
}