package com.wokwi_spring.data.repositories

import com.wokwi_spring.data.configurations.MqttConfiguration
import com.wokwi_spring.data.extensions.toDocument
import com.wokwi_spring.data.models.SensorDataDocument
import com.wokwi_spring.domain.entities.SensorData
import com.wokwi_spring.domain.repositories.SensorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class SensorRepositoryImpl(
    @Autowired private val mongoTemplate: MongoTemplate,
    @Autowired private val mqttGateway: MqttConfiguration.MqttGateway
): SensorRepository {

    override suspend fun getLastMeasurement(): SensorData? {
        val query = Query().apply {
            with(Sort.by(Sort.Direction.DESC, "readDate")).limit(1)
        }

        val foundMeasurements = mongoTemplate.find(query, SensorDataDocument::class.java)

        if (foundMeasurements.isEmpty()) {
            return null
        }

        return foundMeasurements.first().mapToDomain()
    }

    override fun uploadMeasurement(sensorData: SensorData) {
        mongoTemplate.save(sensorData.toDocument())
    }

    override fun sendCommand(command: String?) =
        mqttGateway.sendToMqtt(command)
}
