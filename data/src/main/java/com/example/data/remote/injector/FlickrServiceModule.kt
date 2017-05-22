package com.example.data.remote.injector

import com.example.data.remote.FlickrService
import com.example.data.remote.ServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FlickrServiceModule {

    @Provides
    @Singleton
    fun providesRedditService(): FlickrService {
        return ServiceFactory.createService(FlickrService::class.java, FlickrService.ENDPOINT)
    }

}