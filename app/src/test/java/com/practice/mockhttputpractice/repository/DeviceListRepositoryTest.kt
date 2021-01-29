package com.practice.mockhttputpractice.repository

import com.practice.mockhttputpractice.base.BaseMockApiTest
import com.practice.mockhttputpractice.util.FakeDataGenerateUtil
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.util.concurrent.TimeUnit

class DeviceListRepositoryTest : BaseMockApiTest() {

    private val repo = DeviceListRepository()
    private val fakeDataGenerateUtil = FakeDataGenerateUtil()

    @Test
    fun getAllDeviceDetailsSuccessTest() {

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody("{\"devices\": [\"0000-0001\", \"0000-0002\", \"0000-0003\"]}")
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
                        "0000-0001",
                        "test_name01",
                        "test_owner01"
                    )
                )
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
                        "0000-0002",
                        "test_name02",
                        "test_owner02"
                    )
                )
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
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

    @Test
    fun getAllDeviceDetailsSuccessWithDelayTest() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody("{\"devices\": [\"0000-0001\", \"0000-0002\", \"0000-0003\"]}")
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBodyDelay(100, TimeUnit.MILLISECONDS)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
                        "0000-0001",
                        "test_name01",
                        "test_owner01"
                    )
                )
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
                        "0000-0002",
                        "test_name02",
                        "test_owner02"
                    )
                )
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
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


}