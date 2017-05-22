package com.example.domain.repository

import com.example.domain.models.Photo
import io.reactivex.Single

/**
 * Contract for the list of photos
 */
interface ListContractModel {

    fun getRecentPhotos(perPage: Int, page: Int): Single<List<Photo>>
    fun searchPhotos(perPage: Int, page: Int, text: String): Single<List<Photo>>
    fun saveToLocalStorage(list: List<Photo>)
    fun clearLocalStorage()
    fun getLocalEntries(): List<Photo>

}