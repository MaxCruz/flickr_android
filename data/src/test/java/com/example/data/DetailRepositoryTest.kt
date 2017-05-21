package com.example.data

import com.example.data.remote.FlickrService
import com.example.data.remote.ServiceFactory
import com.example.data.repository.DetailRepository
import com.example.domain.models.PhotoDetail
import org.junit.Before
import org.junit.Test


/**
 * Unit test for the list repository
 */
class DetailRepositoryTest {

    lateinit var repository: DetailRepository

    @Before
    fun setUp() {
        val service = ServiceFactory.createService(FlickrService::class.java,
                FlickrService.ENDPOINT)
        repository = DetailRepository(service)
    }

    @Test
    fun shouldGetRecentPhotos() {
        val observer = repository.getPhotoInfo("33946536664").test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { it is PhotoDetail }
    }

}
