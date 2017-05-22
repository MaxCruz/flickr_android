package com.example.flickr

import android.app.Application
import com.example.data.images.injector.ImageLoaderModule
import com.example.data.local.injector.LocalStorageModule
import com.example.data.remote.injector.FlickrServiceModule
import com.example.flickr.detail.DetailContract
import com.example.flickr.detail.injector.DaggerDetailComponent
import com.example.flickr.detail.injector.DetailComponent
import com.example.flickr.detail.injector.DetailModule
import com.example.flickr.list.ListContract
import com.example.flickr.list.injector.DaggerListComponent
import com.example.flickr.list.injector.ListComponent
import com.example.flickr.list.injector.ListModule
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager


class FlickrApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.Builder(this).openDatabasesOnInit(true).build())
    }

    fun getListComponent(view: ListContract.View): ListComponent {
        return DaggerListComponent.builder()
                .imageLoaderModule(ImageLoaderModule(view.getContext()))
                .flickrServiceModule(FlickrServiceModule())
                .localStorageModule(LocalStorageModule())
                .listModule(ListModule(view))
                .build()
    }

    fun getDetailComponent(view: DetailContract.View): DetailComponent {
        return DaggerDetailComponent.builder()
                .imageLoaderModule(ImageLoaderModule(view.getContext()))
                .flickrServiceModule(FlickrServiceModule())
                .detailModule(DetailModule(view))
                .build()
    }

}