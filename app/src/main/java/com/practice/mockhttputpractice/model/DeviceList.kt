package com.practice.mockhttputpractice.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeviceList(

    @Json(name = "devices")
    val devices: List<String>

)