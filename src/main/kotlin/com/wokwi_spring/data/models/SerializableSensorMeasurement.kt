package com.wokwi_spring.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("data")
data class SerializableSensorMeasurement(
    @SerialName("value") val value: Double?,
    @SerialName("sensor") val sensor: String?
)
