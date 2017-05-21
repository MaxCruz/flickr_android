package com.example.data

import com.example.data.dto.RequestPhotos
import com.example.data.dto.RequestStatus
import com.example.data.remote.FlickrService
import com.example.data.remote.ServiceFactory
import org.junit.Before
import org.junit.Test


/**
 * Unit test for the Flickr API service
 */
class FlickrServiceTest {

    lateinit var service: FlickrService

    @Before
    fun setUp() {
        service = ServiceFactory.createService(FlickrService::class.java, FlickrService.ENDPOINT)
    }

    @Test
    fun shouldGetRecent() {
        val observer = service.getRecent("flickr.photos.getRecent", 10, 1).test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { request ->
            request is RequestPhotos && request.status == RequestStatus.OK
        }
        observer.assertValue { (photosPage) -> photosPage.photos.isNotEmpty() }
    }

}
