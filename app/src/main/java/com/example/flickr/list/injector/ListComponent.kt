package com.example.flickr.list.injector

import com.example.data.images.injector.ImageLoaderModule
import com.example.data.local.injector.LocalStorageModule
import com.example.data.remote.injector.FlickrServiceModule
import com.example.flickr.list.ListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ImageLoaderModule::class, FlickrServiceModule::class,
        LocalStorageModule::class, ListModule::class))
interface ListComponent {

    fun inject(activity: ListActivity)

}