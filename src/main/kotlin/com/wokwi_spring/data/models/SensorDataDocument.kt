package com.wokwi_spring.data.models

import com.wokwi_spring.data.utils.BaseMapper
import com.wokwi_spring.domain.entities.SensorData
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("SensorData")
data class SensorDataDocument(
    @Id val sensorId: ObjectId?,
    val temperature: Double?,
    val humidity: Double?,
    val pir: Int?,
    val readDate: LocalDateTime?
): BaseMapper<SensorData> {

    override fun mapToDomain() =
        SensorData(
            sensorId = sensorId,
            temperature = temperature,
            humidity = humidity,
            pir = pir,
            readDate = readDate
        )
}
