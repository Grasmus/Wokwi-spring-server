package com.wokwi_spring.data.mqtt

import com.wokwi_spring.data.models.SerializableCreateSensorData
import com.wokwi_spring.domain.repositories.SensorRepository

import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.stereotype.Component

@Component
class MqttMessageHandler(
    @Autowired private val sensorRepository: SensorRepository
): MessageHandler {

    override fun handleMessage(message: Message<*>) {
        val data = message.payload.toString()

        val jsonBuilder = Json {
            ignoreUnknownKeys = true
        }

        val createSensorData = jsonBuilder.decodeFromString<SerializableCreateSensorData>(data)

        sensorRepository.uploadMeasurement(createSensorData.mapToDomain())
    }
}
