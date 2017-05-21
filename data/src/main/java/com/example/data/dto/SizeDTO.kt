package com.example.data.dto

import com.google.gson.annotations.SerializedName


/**
 * Image size representation
 */
data class SizeDTO(

    @SerializedName("label")
    val label: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("source")
    val source: String

)