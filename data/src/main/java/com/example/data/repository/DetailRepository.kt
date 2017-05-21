package com.example.data.repository

import com.example.data.remote.FlickrService
import com.example.domain.models.PhotoDetail
import com.example.domain.repository.DetailContractModel
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation for the detail image repository
 */
class DetailRepository(val flickrService: FlickrService): DetailContractModel {

    override fun getPhotoInfo(photoId: String): Observable<PhotoDetail> {
        return flickrService.getInfo(photoId).toObservable().map { (photoInfo) ->
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val takenDate = dateFormat.parse(photoInfo.dates.taken)
            val posted = photoInfo.dates.posted.toInt()
            val postedDate = Date(takenDate.time + posted)
            PhotoDetail(
                    title = photoInfo.title.content,
                    description = photoInfo.description.content,
                    takenDate = takenDate,
                    publishedDate = postedDate,
                    license = photoInfo.license,
                    views = photoInfo.views.toInt(),
                    location = photoInfo.owner.location ?: "",
                    user = photoInfo.owner.userName,
                    name = photoInfo.owner.realName
            )
        }
    }

}