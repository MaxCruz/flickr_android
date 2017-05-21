package com.example.data.images

import android.widget.ImageView

interface ImageLoader {

    fun load(image: ImageView, url: String, placeholder: Int? = null)

}
