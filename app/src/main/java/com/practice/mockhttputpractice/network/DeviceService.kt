package com.practice.mockhttputpractice.network

import com.practice.mockhttputpractice.model.Device
import com.practice.mockhttputpractice.model.DeviceList
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface DeviceService {

    @GET("devices/{id}")
    fun getDeviceList(@Path("id") userId: String): Observable<DeviceList>

    @GET("devices/{serial}")
    fun getDeviceDetail(@Path("serial") serialNumber: String): Observable<Device>

}