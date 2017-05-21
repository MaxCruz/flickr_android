package com.example.domain.models

import java.util.*

/**
 * Business model that extends the information of the photo
 */
data class PhotoDetail (

        val title: String,
        val description: String,
        val takenDate: Date,
        val publishedDate: Date,
        val license: String,
        val views: Int,
        val location: String,
        val user: String,
        val name: String

)