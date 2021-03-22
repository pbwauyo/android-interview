package com.peter.androidinterview.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.domain.models.Photo
import retrofit2.HttpException
import java.io.IOException

/**
 * Support pagination so that data is loaded in chunks. Paging Library 3 is used
 */
class PhotosPagingSource (val api: Api, val albumId: Int): PagingSource<Int, Photo>(){

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val currentKey = params.key ?: 0
        val pageSize = params.loadSize

        return try {
            val photos = api.fetchPhotosByAlbum(albumId, currentKey, pageSize)
            val nextKey = if (photos.isEmpty()) null else photos.size
            LoadResult.Page(
                data = photos,
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