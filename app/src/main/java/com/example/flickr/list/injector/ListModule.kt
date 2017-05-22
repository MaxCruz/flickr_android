package com.example.flickr.list.injector

import com.example.data.images.ImageLoader
import com.example.data.local.LocalStorage
import com.example.data.remote.FlickrService
import com.example.data.repository.ListRepository
import com.example.domain.interactors.GetRecentPhotos
import com.example.domain.interactors.SearchPhotos
import com.example.domain.repository.ListContractModel
import com.example.flickr.list.ListContract
import com.example.flickr.list.ListPresenter
import com.example.flickr.list.adapter.PhotoListAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class ListModule(val view: ListContract.View) {

    @Provides
    @Singleton
    fun providesEntryListAdapter(imageLoader: ImageLoader): PhotoListAdapter {
        return PhotoListAdapter(view, arrayListOf(), imageLoader)
    }

    @Provides
    @Singleton
    fun providesListModel(flickrService: FlickrService, localStorage: LocalStorage):
            ListContractModel {
        return ListRepository(flickrService, localStorage)
    }

    @Provides
    @Singleton
    fun providesGetRecentPhotos(listContractModel: ListContractModel): GetRecentPhotos {
        val subscribeOn = Schedulers.io()
        val observeOn = AndroidSchedulers.mainThread()
        return GetRecentPhotos(listContractModel, subscribeOn, observeOn)
    }

    @Provides
    @Singleton
    fun providesSearchPhotos(listContractModel: ListContractModel): SearchPhotos {
        val subscribeOn = Schedulers.io()
        val observeOn = AndroidSchedulers.mainThread()
        return SearchPhotos(listContractModel, subscribeOn, observeOn)
    }

    @Provides
    @Singleton
    fun providesListPresenter(getRecentPhotos: GetRecentPhotos, searchPhotos: SearchPhotos):
            ListContract.Presenter {
        return ListPresenter(view, getRecentPhotos, searchPhotos)
    }

}