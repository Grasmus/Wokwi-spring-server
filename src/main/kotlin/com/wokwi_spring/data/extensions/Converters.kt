package com.wokwi_spring.data.extensions

import com.wokwi_spring.data.models.SensorDataDocument
import com.wokwi_spring.domain.entities.SensorData

fun SensorData.toDocument() =
    SensorDataDocument(
        sensorId = sensorId,
        temperature = temperature,
        humidity = humidity,
        pir = pir,
        readDate = readDate
    )
