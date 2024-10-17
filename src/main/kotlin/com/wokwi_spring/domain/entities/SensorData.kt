package com.wokwi_spring.domain.entities

import org.bson.types.ObjectId
import java.time.LocalDateTime

data class SensorData(
    val sensorId: ObjectId?,
    val temperature: Double?,
    val humidity: Double?,
    val pir: Int?,
    val readDate: LocalDateTime?
)
