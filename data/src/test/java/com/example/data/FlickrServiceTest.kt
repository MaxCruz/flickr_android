package com.example.data

import com.example.data.dto.RequestPhotoInfoDTO
import com.example.data.dto.RequestPhotosDTO
import com.example.data.dto.RequestSizesDTO
import com.example.data.dto.StatusDTO
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
        val observer = service.getRecent(10, 1).test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { response -> response.code() == 200 }
        observer.assertValue { response ->
            val requestPhotos = response.body()
            requestPhotos is RequestPhotosDTO && requestPhotos.status == StatusDTO.OK
        }
    }

    @Test
    fun shouldGetSizes() {
        val observer = service.getSizes("33943238154").test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { response -> response.code() == 200 }
        observer.assertValue { response ->
            val requestSizes = response.body()
            requestSizes is RequestSizesDTO && requestSizes.status == StatusDTO.OK
        }
    }

    @Test
    fun shouldGetPhotoInfo() {
        val observer = service.getInfo("33943238154").test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { response -> response.code() == 200 }
        observer.assertValue { response ->
            val requestPhotoInfo = response.body()
            requestPhotoInfo is RequestPhotoInfoDTO && requestPhotoInfo.status == StatusDTO.OK
        }
    }

    @Test
    fun shouldSearch() {
        val observer = service.search(10, 1, "dog").test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { response -> response.code() == 200 }
        observer.assertValue { response ->
            val requestPhotos = response.body()
            requestPhotos is RequestPhotosDTO && requestPhotos.status == StatusDTO.OK
        }
    }

}
