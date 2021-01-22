package com.practice.mockhttputpractice.repository

import com.practice.mockhttputpractice.base.BaseMockApiTest
import com.practice.mockhttputpractice.model.Device
import com.squareup.moshi.Moshi
import okhttp3.mockwebserver.MockResponse
import org.junit.Test

class DeviceListRepositoryTest : BaseMockApiTest() {

    private val repo = DeviceListRepository()

    @Test
    fun getAllDeviceDetailsSuccessTest() {

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody("{\"devices\": [\"0000-0001\", \"0000-0002\", \"0000-0003\"]}")
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    generateFakeDeviceDetail(
                        "0000-0001",
                        "test_name01",
                        "test_owner01"
                    )
                )
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    generateFakeDeviceDetail(
                        "0000-0002",
                        "test_name02",
                        "test_owner02"
                    )
                )
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    generateFakeDeviceDetail(
                        "0000-0003",
                        "test_name03",
                        "test_owner03"
                    )
                )
        )


        val testObserver = repo.getAllDeviceDetails("test_user")
            .test()

        testObserver.awaitCount(1)

        testObserver.assertValue {
            it.size == 3 &&
                    it[0].deviceName == "test_name01" &&
                    it[1].deviceName == "test_name02" &&
                    it[2].deviceName == "test_name03"
        }

    }

    private fun generateFakeDeviceDetail(
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