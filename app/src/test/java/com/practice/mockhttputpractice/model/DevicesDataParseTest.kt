package com.practice.mockhttputpractice.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DevicesDataParseTest {

    lateinit var jsonData: String
    lateinit var moshi: Moshi
    lateinit var jsonAdapter: JsonAdapter<DevicesData>

    @Before
    fun initTest() {
        jsonData = "{\"devices\": [{\"serialNumber\": \"000000\",\"deviceName\": \"test name\",\"deviceOwner\": \"test owner\"}]}"
        moshi = Moshi.Builder().build()
        jsonAdapter = moshi.adapter(DevicesData::class.java)
    }

    @Test
    fun devicesDataParseTest() {

        val devicesData = jsonAdapter.fromJson(jsonData)

        Assert.assertNotNull(devicesData)
        Assert.assertEquals(devicesData!!.devices.size, 1)
        Assert.assertEquals(devicesData.devices[0].serialNumber, "000000")
        Assert.assertEquals(devicesData.devices[0].deviceName, "test name")
        Assert.assertEquals(devicesData.devices[0].deviceOwner, "test owner")
    }

}