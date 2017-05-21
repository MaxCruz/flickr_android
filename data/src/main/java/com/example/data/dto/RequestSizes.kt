package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class that hold the recent photos
 */
data class RequestSizes (

        @SerializedName("sizes")
        val sizes: Sizes,
        @SerializedName("stat")
        val status: Status,
        @SerializedName("code")
        val code: Int

)