package com.example.domain.interactors

import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * This abstract class represents an execution unit for different use cases. Any use case in the
 * application should implement this contract. Use cases are the subReddit point to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response parameter
 */
abstract class UseCase<in Q : UseCase.Input, P : UseCase.Output>(val subscribeOn: Scheduler,
                                                                 val observeOn: Scheduler) {

    /**
     * Method to execute the use case
     */
    fun execute(requestValues: Q? = null): Observable<P> {
        return executeUseCase(requestValues).subscribeOn(subscribeOn).observeOn(observeOn)
    }

    /**
     * Abstract method to implement the use case doLogin
     */
    protected abstract fun executeUseCase(values: Q?): Observable<P>

    /**
     * Wrapper for parameters
     */
    interface Input

    /**
     * Wrapper for results
     */
    interface Output

}