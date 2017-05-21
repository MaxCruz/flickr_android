package com.example.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Notes for the picture
 */
data class Notes (

     @SerializedName("note")
     val notes: List<String>

)