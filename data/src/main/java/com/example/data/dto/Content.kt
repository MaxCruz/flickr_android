package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Common content class
 */
data class Content (

        @SerializedName("_content")
        val content: String

)