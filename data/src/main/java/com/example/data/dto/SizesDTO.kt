package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Image sizes container
 */
data class SizesDTO(

    @SerializedName("size")
    val size: List<SizeDTO>

)