package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class that hold the recent photos
 */
data class RequestPhotosDTO(

        @SerializedName("photos")
        val photosPage: PhotosPageDTO,
        @SerializedName("stat")
        val status: StatusDTO,
        @SerializedName("code")
        val code: Int

)