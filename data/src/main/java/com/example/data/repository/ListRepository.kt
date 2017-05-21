package com.example.data.repository

import com.example.data.dto.RequestPhotosDTO
import com.example.data.remote.FlickrService
import com.example.domain.models.Photo
import com.example.domain.repository.ListContractModel
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Implementation for the image list repository
 */
class ListRepository(val flickrService: FlickrService): ListContractModel {

    override fun getRecentPhotos(perPage: Int, page: Int): Observable<Photo> {
        return getObservablePhoto(flickrService.getRecent(perPage, page))
    }

    override fun searchPhotos(perPage: Int, page: Int, text: String): Observable<Photo> {
        return getObservablePhoto(flickrService.search(perPage, page, text))
    }

    private fun getObservablePhoto(single: Single<RequestPhotosDTO>): Observable<Photo> {
        return single
                .toObservable()
                .flatMap { Observable.fromIterable(it.photosPage.photos) }
                .flatMap { (id) ->
                    flickrService.getSizes(id).toObservable()
                            .map { (sizes) ->
                                sizes.size.filter { (label) -> label == "Medium" }.first()
                            }
                            .map { size -> Photo(id, size.source) }
                }
    }

}