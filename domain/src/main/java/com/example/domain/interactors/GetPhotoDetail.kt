package com.example.domain.interactors

import com.example.domain.models.PhotoDetail
import com.example.domain.repository.DetailContractModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.security.InvalidParameterException

/**
 * Use case that load the recent photos
 */
class GetPhotoDetail(val detailModel: DetailContractModel, subscribeOn: Scheduler, observeOn: Scheduler):
        UseCase<GetPhotoDetail.Input, GetPhotoDetail.Output>(subscribeOn, observeOn) {

    override fun executeUseCase(values: Input?): Observable<Output> {
        if (values == null) return Observable.error(InvalidParameterException())
        return detailModel.getPhotoInfo(values.photoId).map(::Output)
    }

    data class Input(val photoId: String): UseCase.Input
    data class Output(val photoDetail: PhotoDetail): UseCase.Output

}