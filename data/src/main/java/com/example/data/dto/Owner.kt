package com.example.data.dto

import com.google.gson.annotations.SerializedName


/**
 * Owner of a picture
 */
data class Owner (

    @SerializedName("username")
    val userName: String,
    @SerializedName("realname")
    val realName: String,
    @SerializedName("location")
    val location: String? = null

)