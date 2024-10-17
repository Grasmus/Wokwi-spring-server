package com.wokwi_spring.domain.usecases

import com.wokwi_spring.domain.repositories.SensorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SendCommandUseCase(
    @Autowired private val sensorRepository: SensorRepository
): BaseUseCase<Unit, SendCommandUseCase.Params>() {

    override suspend fun execute(params: Params?) = sensorRepository.sendCommand(params?.command)

    data class Params(val command: String)
}
