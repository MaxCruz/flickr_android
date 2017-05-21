package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class that hold the photo detail
 */
data class RequestPhotoInfoDTO(

        @SerializedName("photo")
        val photoInfo: PhotoInfoDTO,
        @SerializedName("stat")
        val status: StatusDTO,
        @SerializedName("code")
        val code: Int

)