package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.dto.RequestPhotos
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
                Pair("api_key", BuildConfig.FLICKR_KEY),
                Pair("format", "json"),
                Pair("nojsoncallback", "1")
        )

    }

    @GET("rest")
    fun getRecent(
            @Query("method") method: String,
            @Query("per_page") perPage: Int,
            @Query("page") page: Int,
            @QueryMap defaultOptions: Map<String, String> = DEFAULT_OPTIONS
    ): Single<RequestPhotos>

}