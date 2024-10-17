package com.wokwi_spring.domain.usecases

import com.wokwi_spring.domain.entities.SensorData
import com.wokwi_spring.domain.repositories.SensorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetLastSensorDataUseCase(
    @Autowired private val sensorRepository: SensorRepository
): BaseUseCase<SensorData?, Unit>() {

    override suspend fun execute(params: Unit?) = sensorRepository.getLastMeasurement()
}
