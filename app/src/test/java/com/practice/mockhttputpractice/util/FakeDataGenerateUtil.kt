package com.practice.mockhttputpractice.util

import com.practice.mockhttputpractice.model.Device
import com.practice.mockhttputpractice.model.DeviceList
import com.squareup.moshi.Moshi


class FakeDataGenerateUtil {

    fun generateMockDeviceSerialList(serials: List<String>): String {
        val adapter = Moshi.Builder()
            .build()
            .adapter(DeviceList::class.java)

        val deviceList = DeviceList(serials)

        return adapter.toJson(deviceList)
    }

    fun generateMockDeviceDetail(
        serialNumber: String,
        deviceName: String,
        deviceOwner: String
    ): String {

        val adapter = Moshi.Builder()
            .build()
            .adapter(Device::class.java)

        val device = Device(serialNumber, deviceName, deviceOwner)

        return adapter.toJson(device)
    }

}