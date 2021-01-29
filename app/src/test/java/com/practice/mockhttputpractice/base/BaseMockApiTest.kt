package com.practice.mockhttputpractice.base

import com.practice.mockhttputpractice.network.DeviceService
import com.practice.mockhttputpractice.network.DeviceServiceHelper
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseMockApiTest {

    @get:Rule
    var mockWebServer = MockWebServer()

    lateinit var deviceService: DeviceService

    @Before
    open fun initTest() {
        DeviceServiceHelper.initService("http://" + mockWebServer.hostName + ":" + mockWebServer.port + "/")
        deviceService = DeviceServiceHelper.getApiService()
    }

    @After
    open fun endTest() {
        mockWebServer.shutdown()
    }
}