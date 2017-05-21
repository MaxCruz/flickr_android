package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Picture tags
 */
data class Tags (

     @SerializedName("tag")
     val tags: List<String>

)