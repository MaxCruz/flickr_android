package com.example.data.images.injector

import android.content.Context
import com.example.data.images.ImageLoader
import com.example.data.images.ImageLoaderGlide
import dagger.Module
import dagger.Provides

@Module
class ImageLoaderModule(val context: Context) {

    @Provides
    fun providesImageLoader(): ImageLoader {
        return ImageLoaderGlide(context)
    }

}