package com.example.domain.repository

import com.example.domain.models.Photo
import io.reactivex.Observable

/**
 * Contract for the list of photos
 */
interface ListContractModel {

    fun getRecentPhotos(page: Int): Observable<List<Photo>>
    fun searchPhotos(text: String, page: Int): Observable<List<Photo>>

}