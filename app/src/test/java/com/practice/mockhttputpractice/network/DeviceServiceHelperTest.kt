package com.practice.mockhttputpractice.network

import com.practice.mockhttputpractice.network.DeviceServiceHelper
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException

class DeviceServiceHelperTest {

    @Test
    fun getDeviceServiceNormalSetting() {
        DeviceServiceHelper.initService("https://localhost/")
        val deviceService = DeviceServiceHelper.getApiService()

        Assert.assertNotNull(deviceService)
    }

    @Test
    fun getDeviceServiceNoApiUrl() {
        try {
            DeviceServiceHelper.getApiService()
            Assert.fail("Must throw exception.")
        } catch (e: IllegalArgumentException) {
            Assert.assertNotNull(e.message)
        }
    }

}