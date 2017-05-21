package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class that hold the photo detail
 */
data class RequestPhotoInfo (

        @SerializedName("photo")
        val photoInfo: PhotoInfo,
        @SerializedName("stat")
        val status: Status,
        @SerializedName("code")
        val code: Int

)