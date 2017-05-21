package com.example.data.images

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

/**
 * Implementation for the image loader using Glide lib
 */
class ImageLoaderGlide(context: Context) : ImageLoader {

    val requestManager: RequestManager = Glide.with(context)

    override fun load(image: ImageView, url: String, placeholder: Int?) {
        requestManager.load(url).into(image)
    }

}