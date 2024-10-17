package com.wokwi_spring.domain.repositories

import com.wokwi_spring.domain.entities.SensorData
import org.springframework.stereotype.Component

@Component
interface SensorRepository {
    suspend fun getLastMeasurement(): SensorData?
    fun uploadMeasurement(sensorData: SensorData)
    fun sendCommand(command: String?)
}
