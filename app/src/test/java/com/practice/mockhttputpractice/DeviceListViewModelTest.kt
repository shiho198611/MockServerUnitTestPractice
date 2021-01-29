package com.practice.mockhttputpractice

import androidx.lifecycle.Observer
import com.practice.mockhttputpractice.model.Device
import com.practice.mockhttputpractice.repository.DeviceListRepository
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DeviceListViewModelTest {

    lateinit var deviceListViewModel: DeviceListViewModel
    lateinit var deviceListRepository: DeviceListRepository

    lateinit var fakeResponseSubject: PublishSubject<List<Device>>

    lateinit var mockListDataObserver: Observer<List<Device>>
    lateinit var mockErrMsgObserver: Observer<String>

    @Before
    fun initTest() {
        deviceListViewModel = DeviceListViewModel()
        deviceListRepository = Mockito.mock(DeviceListRepository::class.java)

        fakeResponseSubject = PublishSubject.create()

        Mockito.`when`(deviceListRepository.getAllDeviceDetails(Mockito.anyString()))
            .thenReturn(fakeResponseSubject)

        val listDataMock = Mockito.mock(Observer::class.java)
        mockListDataObserver = listDataMock as Observer<List<Device>>

        val errMsgMock = Mockito.mock(Observer::class.java)
        mockErrMsgObserver = errMsgMock as Observer<String>

        deviceListViewModel.deviceDetailList.observeForever(mockListDataObserver)
        deviceListViewModel.errorMsg.observeForever(mockErrMsgObserver)

        deviceListViewModel.deviceListRepository = deviceListRepository
    }

    @Test
    fun updateDeviceDetailListTest() {

        val testInfo = listOf(Device("0001", "name", "owner"))

        deviceListViewModel.updateDeviceDetailList("")
        fakeResponseSubject.onNext(testInfo)

        Mockito.verify(mockListDataObserver).onChanged(testInfo)
    }

}