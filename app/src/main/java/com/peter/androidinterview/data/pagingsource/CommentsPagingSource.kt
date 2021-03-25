package com.peter.androidinterview.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.domain.models.Comment
import retrofit2.HttpException
import java.io.IOException

/**
 * Support pagination so that data is loaded in chunks. Paging Library 3 is used
 */
class CommentsPagingSource(val api: Api, val postId: Int) : PagingSource<Int, Comment>(){
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        val currentKey = params.key ?: 0
        val pageSize = params.loadSize

        return try {
            val comments = api.fetchCommentsByPost(postId, currentKey, pageSize)
            val nextKey = if (comments.isEmpty()) null else comments.size
            LoadResult.Page(
                data = comments,
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