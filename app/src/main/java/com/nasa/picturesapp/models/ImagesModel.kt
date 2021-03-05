package com.nasa.picturesapp.models

import com.nasa.picturesapp.utils.DateAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ImageModel(
    @Transient @SerialName("copyright") val copyRight: String = "",
    @SerialName("date") @Serializable(with = DateAsStringSerializer::class) val date: Date,
    @SerialName("explanation") val explanation: String,
    @SerialName("hdurl") val hdUrl: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("service_version") val serviceVersion: String,
    @SerialName("title") val title: String,
    @SerialName("url") val url: String,
)
