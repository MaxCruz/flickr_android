package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Pagination object
 */
data class PhotosPage(

    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("photo")
    val photos: List<Photo>

)