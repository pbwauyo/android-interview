package com.peter.androidinterview.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.domain.models.Album
import retrofit2.HttpException
import java.io.IOException

/**
 * Support pagination so that data is loaded in chunks. Paging Library 3 is used
 */
class AlbumsPagingSource(val api: Api, val userId:Int) : PagingSource<Int, Album>(){
    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val currentKey = params.key ?: 0
        val pageSize = params.loadSize

        return try {
            val albums = api.fetchAlbumsByUser(userId, currentKey, pageSize)
            val nextKey = if (albums.isEmpty()) null else albums.size
            LoadResult.Page(
                data = albums,
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