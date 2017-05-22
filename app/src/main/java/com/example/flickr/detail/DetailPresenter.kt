package com.example.flickr.detail

import com.example.domain.interactors.GetPhotoDetail
import io.reactivex.disposables.CompositeDisposable

class DetailPresenter(val view: DetailContract.View, val getPhotoDetail: GetPhotoDetail):
        DetailContract.Presenter {

    val subscriptions = CompositeDisposable()

    override fun loadPhotoData(photoId: String) {
        val subscription = getPhotoDetail.execute(GetPhotoDetail.Input(photoId))
                . subscribe({ (photoDetail) -> view.loadData(photoDetail) }, { view.showError() })
        subscriptions.add(subscription)
    }

    override fun disposeAll() {
        subscriptions.clear()
    }

}