package com.example.flickr.list

import com.example.domain.exceptions.RequestErrorException
import com.example.domain.interactors.GetRecentPhotos
import com.example.domain.interactors.SearchPhotos
import com.example.domain.models.Photo
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Presenter for the list of photos
 */
class ListPresenter(val view: ListContract.View, val getRecentPhotos: GetRecentPhotos,
                    val searchPhotos: SearchPhotos) : ListContract.Presenter {

    val subscriptions = CompositeDisposable()

    override fun loadEntries(page: Int) {
        applySubscriber(getRecentPhotos.execute(GetRecentPhotos.Input(page)).map { it.list })
    }

    override fun searchEntries(page: Int, text: String) {
        applySubscriber(searchPhotos.execute(SearchPhotos.Input(page, text)).map { it.list })
    }

    private fun applySubscriber(observable: Observable<List<Photo>>) {
        view.showProgressIndicator(true)
        val subscription = observable.subscribe (
                { list ->
                    view.showProgressIndicator(false)
                    view.appendEntries(list)
                },
                { error ->
                    view.showProgressIndicator(false)
                    if (error is RequestErrorException)
                        error.message?.let { message -> view.showError(message) }
                }
        )
        subscriptions.add(subscription)
    }

    override fun disposeAll() {
        subscriptions.clear()
    }

}