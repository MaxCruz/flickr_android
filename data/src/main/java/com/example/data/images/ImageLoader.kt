package com.example.data.images

import android.widget.ImageView

/**
 * Interface to load images in a view
 */
interface ImageLoader {

    fun load(image: ImageView, url: String, placeholder: Int? = null)

}
