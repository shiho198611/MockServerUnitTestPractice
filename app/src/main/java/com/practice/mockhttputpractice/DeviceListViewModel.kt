package com.practice.mockhttputpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.mockhttputpractice.model.Device

class DeviceListViewModel: ViewModel() {

    val deviceDetailList = MutableLiveData<List<Device>>()


}