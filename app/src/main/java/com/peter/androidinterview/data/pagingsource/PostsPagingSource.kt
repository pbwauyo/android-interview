package com.peter.androidinterview.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.domain.models.Post
import retrofit2.HttpException
import java.io.IOException

/**
 * Support pagination so that data is loaded in chunks. Paging Library 3 is used
 */
class PostsPagingSource(val api: Api, val userId: Int) : PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val currentKey = params.key ?: 0
            val loadSize = params.loadSize
            val posts = api.fetchPostsByUser(userId, currentKey, loadSize)
            val nextKey = if (posts.isEmpty()) null else posts.size

            LoadResult.Page(
                data = posts,
                prevKey = null,
                nextKey = nextKey
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}