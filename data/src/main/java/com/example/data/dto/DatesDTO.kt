package com.example.data.dto

import com.google.gson.annotations.SerializedName


/**
 * Picture related dates
 */
data class DatesDTO(

    @SerializedName("posted")
    val posted: String,
    @SerializedName("taken")
    val taken: String

)