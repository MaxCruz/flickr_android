package com.example.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Factory to create the remote API client
 *
 * @author Max
 */
class ServiceFactory {

    companion object {

        fun <T> createService(clazz: Class<T>, endPoint: String): T {
            val httpClientBuilder = OkHttpClient.Builder()
            val retrofit = Retrofit.Builder()
                    .baseUrl(endPoint)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build()
            val service = retrofit.create(clazz)
            return service
        }

    }

}