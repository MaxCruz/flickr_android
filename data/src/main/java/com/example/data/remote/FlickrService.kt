package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.dto.RequestPhotoInfoDTO
import com.example.data.dto.RequestPhotosDTO
import com.example.data.dto.RequestSizesDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * API endpoints from Flickr
 */
interface FlickrService {

    companion object {

        val ENDPOINT = "https://api.flickr.com/services/"
        val DEFAULT_OPTIONS = mapOf(
                Pair("format", "json"),
                Pair("nojsoncallback", "1"),
                Pair("api_key", BuildConfig.FLICKR_KEY)
        )

    }

    @GET("rest")
    fun getRecent(
            @Query("per_page") perPage: Int,
            @Query("page") page: Int,
            @Query("method") method: String = "flickr.photos.getRecent",
            @QueryMap defaultOptions: Map<String, String> = DEFAULT_OPTIONS
    ): Single<RequestPhotosDTO>

    @GET("rest")
    fun getSizes(
            @Query("photo_id") photoId: String,
            @Query("method") method: String = "flickr.photos.getSizes",
            @QueryMap defaultOptions: Map<String, String> = DEFAULT_OPTIONS
    ): Single<RequestSizesDTO>

    @GET("rest")
    fun getInfo(
            @Query("photo_id") photoId: String,
            @Query("method") method: String = "flickr.photos.getInfo",
            @QueryMap defaultOptions: Map<String, String> = DEFAULT_OPTIONS
    ): Single<RequestPhotoInfoDTO>

    @GET("rest")
    fun search(
            @Query("per_page") perPage: Int,
            @Query("page") page: Int,
            @Query("text") text: String,
            @Query("method") method: String = "flickr.photos.search",
            @QueryMap defaultOptions: Map<String, String> = DEFAULT_OPTIONS
    ): Single<RequestPhotosDTO>

}