package com.practice.mockhttputpractice.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DevicesData(

    @Json(name = "devices")
    val devices: List<Device>

)