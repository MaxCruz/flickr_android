package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Possible response status from the awful Flickr's API
 */
enum class StatusDTO {
    @SerializedName("fail") FAIL, @SerializedName("ok") OK
}