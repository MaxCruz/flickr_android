package com.example.data

import android.content.Context
import com.example.data.local.LocalStorage
import com.example.data.remote.FlickrService
import com.example.data.remote.ServiceFactory
import com.example.data.repository.ListRepository
import com.example.domain.models.Photo
import com.raizlabs.android.dbflow.config.FlowManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * Unit test for the list repository
 */
class ListRepositoryTest {

    @Mock
    lateinit var mockContext: Context
    lateinit var repository: ListRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        FlowManager.init(mockContext)
        val service = ServiceFactory.createService(FlickrService::class.java,
                FlickrService.ENDPOINT)
        val local = LocalStorage()
        repository = ListRepository(service, local)
    }

    @Test
    fun shouldGetRecentPhotos() {
        val observer = repository.getRecentPhotos(1, 1).test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { it is List<Photo> }
    }

    @Test
    fun shouldSearchPhotos() {
        val observer = repository.searchPhotos(10, 1, "guitar").test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertValue { it is List<Photo> }
    }

}
