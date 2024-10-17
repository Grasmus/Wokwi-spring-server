package com.wokwi_spring.data.models

import com.wokwi_spring.data.constants.SensorType
import com.wokwi_spring.data.utils.BaseMapper
import com.wokwi_spring.domain.entities.SensorData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Serializable
@SerialName("SensorData")
data class SerializableCreateSensorData(
    @SerialName("time") val readDate: String?,
    @SerialName("data") val dataList: List<SerializableSensorMeasurement>
): BaseMapper<SensorData> {

    override fun mapToDomain(): SensorData {
        var humidity: Double? = null
        var temperature: Double? = null
        var pir: Int? = null

        for (data in dataList) {
            when (data.sensor) {
                SensorType.Humidity.name.lowercase() -> {
                    humidity = data.value
                }

                SensorType.Temperature.name.lowercase() -> {
                    temperature = data.value
                }

                SensorType.Pir.name.lowercase() -> {
                    pir = data.value?.toInt()
                }
            }
        }

        return SensorData(
            sensorId = null,
            temperature = temperature,
            humidity = humidity,
            pir = pir,
            readDate = readDate?.toLong()?.let { date ->
                LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(date),
                    TimeZone.getDefault().toZoneId()
                )
            }
        )
    }
}
