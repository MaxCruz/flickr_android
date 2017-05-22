package com.example.flickr.detail.injector

import com.example.data.remote.FlickrService
import com.example.data.repository.DetailRepository
import com.example.domain.interactors.GetPhotoDetail
import com.example.domain.repository.DetailContractModel
import com.example.flickr.detail.DetailContract
import com.example.flickr.detail.DetailPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DetailModule(val view: DetailContract.View) {

    @Singleton
    @Provides
    fun providesDetailContractModel(flickrService: FlickrService): DetailContractModel {
        return DetailRepository(flickrService)
    }

    @Singleton
    @Provides
    fun providesGetPhotoDetail(detailContractModel: DetailContractModel): GetPhotoDetail {
        val subscribeOn = Schedulers.io()
        val observeOn = AndroidSchedulers.mainThread()
        return GetPhotoDetail(detailContractModel, subscribeOn, observeOn)
    }

    @Singleton
    @Provides
    fun providesDetailPresenter(getPhotoDetail: GetPhotoDetail): DetailContract.Presenter {
        return DetailPresenter(view, getPhotoDetail)
    }

}