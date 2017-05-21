package com.example.domain.repository

import com.example.domain.models.Photo
import io.reactivex.Observable

/**
 * Contract for the list of photos
 */
interface ListContractModel {

    fun getRecentPhotos(perPage: Int, page: Int): Observable<Photo>
    fun searchPhotos(perPage: Int, page: Int, text: String): Observable<Photo>

}