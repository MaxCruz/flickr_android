package com.example.data.dto

import com.google.gson.annotations.SerializedName


/**
 * Image size representation
 */
data class Size (

    @SerializedName("label")
    val label: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("url")
    val url: String

)