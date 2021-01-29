package com.practice.mockhttputpractice.repository

import com.practice.mockhttputpractice.base.BaseMockApiTest
import com.practice.mockhttputpractice.model.Device
import com.practice.mockhttputpractice.util.FakeDataGenerateUtil
import okhttp3.mockwebserver.MockResponse
import org.junit.Test

/**
 * @ClassName: DeviceListRepositoryParalleTest
 * @Description:
 * @Date: 2021/1/29 4:43 PM
 * @History:
 * <date> <version> <desc>
 */
class DeviceListRepositoryParallelTest : BaseMockApiTest() {

    private val repo = DeviceListRepository()
    private val fakeDataGenerateUtil = FakeDataGenerateUtil()

    @Test
    fun getAllDeviceDetailsParallelSuccessTest() {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(fakeDataGenerateUtil.generateMockDeviceSerialList(listOf("0000", "0001")))
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
                        "0000",
                        "name00",
                        "owner00"
                    )
                )
        )

        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    fakeDataGenerateUtil.generateMockDeviceDetail(
                        "0001",
                        "name01",
                        "owner01"
                    )
                )
        )

        val testObserver = repo.getAllDeviceDetailsParallel("").test()
        testObserver.awaitCount(3)

        val verifyData = arrayOf(
            Device("0000", "name00", "owner00"),
            Device("0001", "name01", "owner01")
        )

        testObserver.assertValues(*verifyData)
    }

}