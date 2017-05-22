package com.example.flickr.detail.injector

import com.example.data.remote.injector.FlickrServiceModule
import com.example.flickr.detail.DetailActivity
import com.example.data.images.injector.ImageLoaderModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ImageLoaderModule::class, FlickrServiceModule::class,
        DetailModule::class))
interface DetailComponent {

    fun inject(activity: DetailActivity)

}