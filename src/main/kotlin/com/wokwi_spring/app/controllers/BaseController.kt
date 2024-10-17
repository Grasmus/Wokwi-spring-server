package com.wokwi_spring.app.controllers

import com.wokwi_spring.domain.entities.exception.UnexpectedErrorException
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

abstract class BaseController {

    protected suspend fun <RESULT> safeExecuteSuspend(call: suspend () -> RESULT) =
        try {
            call()
        } catch (exception: UnexpectedErrorException) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.message
            )
        }
}
