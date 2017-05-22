package com.example.flickr.detail

import android.content.Context
import com.example.domain.models.PhotoDetail

interface DetailContract {

    interface View {

        fun loadData(detail: PhotoDetail)
        fun showError()
        fun getContext(): Context

    }

    interface Presenter {

        fun loadPhotoData(photoId: String)
        fun disposeAll()

    }

}