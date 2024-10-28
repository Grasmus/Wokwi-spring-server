package com.wokwi_spring.data.mqtt

import com.wokwi_spring.data.constants.COMMAND_TURN_OFF_SERVO
import com.wokwi_spring.data.constants.COMMAND_TURN_ON_SERVO
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

        val sensorData = jsonBuilder
            .decodeFromString<SerializableCreateSensorData>(data)
            .mapToDomain()

        when (sensorData.pir) {
            0 -> sensorRepository.sendCommand(COMMAND_TURN_OFF_SERVO)
            1 -> sensorRepository.sendCommand(COMMAND_TURN_ON_SERVO)
        }

        sensorRepository.uploadMeasurement(sensorData)
    }
}
