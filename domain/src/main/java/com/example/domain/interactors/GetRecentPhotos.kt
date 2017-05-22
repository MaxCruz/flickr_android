package com.example.domain.interactors

import com.example.domain.models.Photo
import com.example.domain.repository.ListContractModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.security.InvalidParameterException

/**
 * Use case that load the recent photos
 */
class GetRecentPhotos(val listModel: ListContractModel, subscribeOn: Scheduler, observeOn: Scheduler):
        UseCase<GetRecentPhotos.Input, GetRecentPhotos.Output>(subscribeOn, observeOn) {

    val PER_PAGE = 10

    override fun executeUseCase(values: Input?): Observable<Output> {
        if (values == null) return Observable.error(InvalidParameterException())
        return listModel.getRecentPhotos(PER_PAGE, values.page)
                .toObservable()
                .doOnNext { listModel.saveToLocalStorage(it) }
                .onErrorReturn { listModel.getLocalEntries() }
                .map(::Output)
    }

    data class Input(val page: Int): UseCase.Input
    data class Output(val list: List<Photo>): UseCase.Output

}