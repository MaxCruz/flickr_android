package com.example.data.repository

import com.example.data.dto.RequestPhotoInfoDTO
import com.example.data.dto.StatusDTO
import com.example.data.remote.FlickrService
import com.example.domain.exceptions.OfflineModeException
import com.example.domain.exceptions.RequestErrorException
import com.example.domain.exceptions.UnexpectedHttpCodeException
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
        return flickrService.getInfo(photoId)
                .toObservable()
                .map { response ->
                    when (response.code()) {
                        200 -> response.body() as RequestPhotoInfoDTO
                        503 -> throw OfflineModeException()
                        else -> throw UnexpectedHttpCodeException()
                    }
                }
                .map { (photoInfo, status, message) ->
                    if (status == StatusDTO.FAIL) throw RequestErrorException(message)
                    photoInfo
                }
                .map { (_, license, owner, title, description, dates, views) ->
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val takenDate = dateFormat.parse(dates.taken)
                    val posted = dates.posted.toInt()
                    val postedDate = Date(takenDate.time + posted)
                    PhotoDetail(
                            title = title.content,
                            description = description.content,
                            takenDate = takenDate,
                            publishedDate = postedDate,
                            license = license,
                            views = views.toInt(),
                            location = owner.location ?: "",
                            user = owner.userName,
                            name = owner.realName
                    )
                }
    }

}