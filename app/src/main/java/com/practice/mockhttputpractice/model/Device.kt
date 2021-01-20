package com.practice.mockhttputpractice.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Device(

    @Json(name = "serialNumber")
    val serialNumber: String,

    @Json(name = "deviceName")
    val deviceName: String,

    @Json(name = "deviceOwner")
    val deviceOwner: String

)