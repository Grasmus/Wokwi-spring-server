package com.wokwi_spring.app.controllers

import com.wokwi_spring.domain.usecases.GetLastSensorDataUseCase
import com.wokwi_spring.domain.usecases.SendCommandUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api")
class SensorController(
    @Autowired private val getLastSensorDataUseCase: GetLastSensorDataUseCase,
    @Autowired private val sendCommandUseCase: SendCommandUseCase
): BaseController() {

    @GetMapping("/devices-data")
    suspend fun getLastMeasurement() =
        safeExecuteSuspend {
            getLastSensorDataUseCase.invoke()
                ?: throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Measurement not found"
                )
        }

    @PostMapping("/send-command/{command}")
    suspend fun sendCommand(@PathVariable command: String) =
        safeExecuteSuspend {
            sendCommandUseCase.invoke(
                SendCommandUseCase.Params(command)
            )
        }
}
