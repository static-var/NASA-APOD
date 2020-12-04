package com.example.nasaapod.data.model


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data class which holds information of an APOD
 */
@Keep
@JsonClass(generateAdapter = true)
data class ApodImage(
    val title: String = "",
    val date: String = "",
    val explanation: String = "",
    val url: String = "",
    @Json(name = "hdurl")
    val hdUrl: String = "",
    @Json(name = "media_type")
    val mediaType: String = "",
    @Json(name = "service_version")
    val serviceVersion: String = "",
    val copyright: String? = null
)