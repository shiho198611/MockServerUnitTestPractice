package com.practice.mockhttputpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.mockhttputpractice.model.Device
import com.practice.mockhttputpractice.repository.DeviceListRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.IllegalStateException

class DeviceListViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val deviceDetailList: MutableLiveData<List<Device>> by lazy {
        MutableLiveData<List<Device>>()
    }

    val errorMsg: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    lateinit var deviceListRepository: DeviceListRepository

    fun updateDeviceDetailList(userId: String) {
        if(!::deviceListRepository.isInitialized) throw IllegalStateException("Must inject ")

        val disposable = deviceListRepository.getAllDeviceDetails(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deviceDetailList.value = it
            }, {
                errorMsg.value = it.message
            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}