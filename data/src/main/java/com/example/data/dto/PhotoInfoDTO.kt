package com.example.data.dto

import com.google.gson.annotations.SerializedName


/**
 * Data class with the details of a photo
 */
data class PhotoInfoDTO(

        @SerializedName("id")
    val id: String,
        @SerializedName("license")
    val license: String,
        @SerializedName("owner")
    val owner: OwnerDTO,
        @SerializedName("title")
    val title: ContentDTO,
        @SerializedName("description")
    val description: ContentDTO,
        @SerializedName("dates")
    val dates: DatesDTO,
        @SerializedName("views")
    val views: String

)