package com.practice.mockhttputpractice.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object DeviceServiceHelper {

    private lateinit var apiUrl: String
    private lateinit var deviceService: DeviceService

    fun initService(url: String) {
        apiUrl = url

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(apiUrl)
            .build()
        deviceService = retrofit.create(DeviceService::class.java)
    }

    fun getApiService(): DeviceService {
        if (!::apiUrl.isInitialized) {
            throw IllegalArgumentException("Api url must to be setting.")
        }

        return deviceService
    }

}