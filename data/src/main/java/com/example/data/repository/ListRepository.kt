package com.example.data.repository

import com.example.data.dto.RequestPhotosDTO
import com.example.data.dto.RequestSizesDTO
import com.example.data.dto.StatusDTO
import com.example.data.remote.FlickrService
import com.example.domain.exceptions.OfflineModeException
import com.example.domain.exceptions.RequestErrorException
import com.example.domain.exceptions.UnexpectedHttpCodeException
import com.example.domain.models.Photo
import com.example.domain.repository.ListContractModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

/**
 * Implementation for the image list repository
 */
class ListRepository(val flickrService: FlickrService) : ListContractModel {

    override fun getRecentPhotos(perPage: Int, page: Int): Observable<Photo> {
        return getObservablePhoto(flickrService.getRecent(perPage, page))
    }

    override fun searchPhotos(perPage: Int, page: Int, text: String): Observable<Photo> {
        return getObservablePhoto(flickrService.search(perPage, page, text))
    }

    private fun getObservablePhoto(single: Single<Response<RequestPhotosDTO>>): Observable<Photo> {
        return single
                .toObservable()
                .map { response ->
                    when (response.code()) {
                        200 -> response.body() as RequestPhotosDTO
                        503 -> throw OfflineModeException()
                        else -> throw UnexpectedHttpCodeException()
                    }
                }
                .doOnNext { (_, status, message) ->
                    if (status == StatusDTO.FAIL) {
                        throw RequestErrorException(message)
                    }
                }
                .flatMap { Observable.fromIterable(it.photosPage.photos) }
                .flatMap { (id) ->
                    flickrService.getSizes(id)
                            .toObservable()
                            .map { response ->
                                when (response.code()) {
                                    200 -> response.body() as RequestSizesDTO
                                    503 -> throw OfflineModeException()
                                    else -> throw UnexpectedHttpCodeException()
                                }
                            }
                            .map { (sizes) ->
                                sizes.size.filter { (label) -> label == "Medium" }.first()
                            }
                            .map { size -> Photo(id, size.source) }
                }

    }

}