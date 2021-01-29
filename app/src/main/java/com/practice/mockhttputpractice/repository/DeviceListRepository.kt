package com.practice.mockhttputpractice.repository

import com.practice.mockhttputpractice.model.Device
import com.practice.mockhttputpractice.network.DeviceServiceHelper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.internal.operators.observable.ObservableEmpty

class DeviceListRepository : BaseRepository {

    fun getAllDeviceDetails(userId: String): Observable<List<Device>> {

        return DeviceServiceHelper.getApiService()
            .getDeviceList(userId)
            .flatMap {

                val iterator = it.devices.asSequence().map { serial ->
                    DeviceServiceHelper.getApiService().getDeviceDetail(serial)
                }.asIterable()

                val detailsObservable = Observable.zip(
                    iterator
                ) { details ->
                    details?.asList()?.map { item ->
                        item as Device
                    } ?: emptyList()
                }
                detailsObservable
            }
    }

    fun getAllDeviceDetailsParallel(userId: String): Observable<Device> {
        return DeviceServiceHelper.getApiService()
            .getDeviceList(userId)
            .flatMap { details ->

                val detailObArray =
                    Array<Observable<Device>>(details.devices.size) { ObservableEmpty.empty() }
                details.devices.forEachIndexed { index, serial ->
                    detailObArray[index] =
                        DeviceServiceHelper.getApiService().getDeviceDetail(serial)
                }

                Observable.concatArray(*detailObArray)
            }
    }

}