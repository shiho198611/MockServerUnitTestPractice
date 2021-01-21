package com.practice.mockhttputpractice.network

import com.practice.mockhttputpractice.model.DeviceList
import com.squareup.moshi.Moshi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MockServerTest {

    lateinit var deviceService: DeviceService

    @get:Rule
    var mockWebServer = MockWebServer()

    @Before
    fun initTest() {
        DeviceServiceHelper.initService("http://" + mockWebServer.hostName + ":" + mockWebServer.port + "/")
        deviceService = DeviceServiceHelper.getApiService()
    }

    @Test
    fun mockServerGetDeviceListTest() {

        val devices = DeviceList(listOf("0000-0001", "0000-0002", "0000-0003"))
        val jsonAdapter = Moshi.Builder().build().adapter(DeviceList::class.java)

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(jsonAdapter.toJson(devices))
        )

        val testObserver = deviceService
            .getDeviceList("test_id")
            .test()

        testObserver.awaitCount(1)

        testObserver.assertValue {

            return@assertValue it.devices[0] == "0000-0001"
                    && it.devices[1] == "0000-0002"
                    && it.devices[2] == "0000-0003"
        }
    }

    @After
    fun endTesting() {
        mockWebServer.shutdown()
    }

}