package com.example.data

import com.example.data.remote.FlickrService
import com.example.data.remote.ServiceFactory
import com.example.data.repository.ListRepository
import com.example.domain.models.Photo
import org.junit.Before
import org.junit.Test


/**
 * Unit test for the list repository
 */
class ListRepositoryTest {

    lateinit var repository: ListRepository

    @Before
    fun setUp() {
        val service = ServiceFactory.createService(FlickrService::class.java,
                FlickrService.ENDPOINT)
        repository = ListRepository(service)
    }

    @Test
    fun shouldGetRecentPhotos() {
        val observer = repository.getRecentPhotos(1, 1).test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { it is Photo }
    }

    @Test
    fun shouldSearchPhotos() {
        val observer = repository.searchPhotos(1, 1, "guitar").test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { it is Photo }
    }

}
