package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class that hold the recent photos
 */
data class RequestPhotos (

        @SerializedName("photos")
        val photosPage: PhotosPage,
        @SerializedName("stat")
        val status: Status,
        @SerializedName("code")
        val code: Int

)