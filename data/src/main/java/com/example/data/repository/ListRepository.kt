package com.example.data.repository

import com.example.data.dao.PhotoDAO
import com.example.data.dto.RequestPhotosDTO
import com.example.data.dto.StatusDTO
import com.example.data.local.LocalStorage
import com.example.data.remote.FlickrService
import com.example.domain.exceptions.RequestErrorException
import com.example.domain.models.Photo
import com.example.domain.repository.ListContractModel
import io.reactivex.Observable
import io.reactivex.Single


/**
 * Implementation for the image list repository
 */
class ListRepository(val flickrService: FlickrService, val localStorage: LocalStorage) :
        ListContractModel {

    override fun getRecentPhotos(perPage: Int, page: Int): Single<List<Photo>> {
        return getObservablePhoto(flickrService.getRecent(perPage, page))
    }

    override fun searchPhotos(perPage: Int, page: Int, text: String): Single<List<Photo>> {
        return getObservablePhoto(flickrService.search(perPage, page, text))
    }

    override fun saveToLocalStorage(list: List<Photo>) {
        val daoList = list.map(::PhotoDAO)
        localStorage.saveObjectList(PhotoDAO::class.java, daoList)
    }

    override fun clearLocalStorage() {
        localStorage.deleteAllObjects(PhotoDAO::class.java)
    }

    override fun getLocalEntries(): List<Photo> {
        return localStorage.retrieveAllObjects(PhotoDAO::class.java).map(PhotoDAO::toPhoto)
    }

    private fun getObservablePhoto(single: Single<RequestPhotosDTO>): Single<List<Photo>> {
        return single
                .toObservable()
                .map { (photosPage, status, message) ->
                    if (status == StatusDTO.FAIL) throw RequestErrorException(message)
                    photosPage
                }
                .flatMap { Observable.fromIterable(it.photos) }
                .flatMap { (id) ->
                    flickrService.getSizes(id)
                            .toObservable()
                            .map { (sizes, status, message) ->
                                if (status == StatusDTO.FAIL) throw RequestErrorException(message)
                                sizes
                            }
                            .map { (size) -> size.filter { (label) -> label == "Medium" }.first() }
                            .map { size -> Photo(id, size.source) }
                }
                .toList()

    }

}