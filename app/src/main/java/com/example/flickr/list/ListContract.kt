package com.example.flickr.list

import android.content.Context
import android.support.v7.widget.SearchView
import com.example.domain.models.Photo

/**
 * MVP operation for the main list. In this contract is just defined the view and the presenter,
 * the model contract is separated in the domain layer
 *
 * @author Max Cruz
 */
interface ListContract {

    /**
     * Operations that the presenter can invoke from the view
     */
    interface View {

        fun showProgressIndicator(display: Boolean)
        fun showError(message: String)
        fun clearEntries()
        fun appendEntries(list: List<Photo>)
        fun goToEntryDetail(photoId: String, url: String)
        fun getContext(): Context

    }

    /**
     * Operations offered to the view to interact with the domain layer
     */
    interface Presenter {

        fun loadEntries(page: Int)
        fun searchEntries(page: Int, text: String)
        fun disposeAll()

    }

}
