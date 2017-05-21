package com.example.data.dto

import com.google.gson.annotations.SerializedName


/**
 * Data class with the details of a photo
 */
data class PhotoInfo (

    @SerializedName("id")
    val id: String,
    @SerializedName("license")
    val license: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("title")
    val title: Content,
    @SerializedName("description")
    val description: Content,
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("views")
    val views: String,
    @SerializedName("notes")
    val notes: Notes,
    @SerializedName("tags")
    val tags: Tags

)