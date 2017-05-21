package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class to represent a photo
 */
data class Photo(

        @SerializedName("id")
        val id: String,
        @SerializedName("owner")
        val owner: String,
        @SerializedName("title")
        val title: String

)