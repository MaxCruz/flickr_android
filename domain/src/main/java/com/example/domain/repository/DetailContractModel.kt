package com.example.domain.repository

import com.example.domain.models.PhotoDetail
import io.reactivex.Observable

/**
 * Contract for the photo detail
 */
interface DetailContractModel {

    fun getPhotoInfo(photoId: String): Observable<PhotoDetail>

}