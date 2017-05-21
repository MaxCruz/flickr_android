package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Image sizes container
 */
data class Sizes (

    @SerializedName("size")
    val size: List<Size>

)