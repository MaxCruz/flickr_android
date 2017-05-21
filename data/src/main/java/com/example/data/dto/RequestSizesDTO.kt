package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class that hold the different sizes of images
 */
data class RequestSizesDTO(

        @SerializedName("sizes")
        val sizes: SizesDTO,
        @SerializedName("stat")
        val status: StatusDTO,
        @SerializedName("code")
        val code: Int

)