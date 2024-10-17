package com.wokwi_spring.domain.usecases

import com.wokwi_spring.domain.entities.exception.UnexpectedErrorException
import org.slf4j.LoggerFactory

abstract class BaseUseCase<RESULT, PARAMS>: UseCase<RESULT, PARAMS> {

    private var logger = LoggerFactory.getLogger(BaseUseCase::class.java)

    suspend operator fun invoke(
        params: PARAMS? = null
    ): RESULT {
        try {
            return execute(params)
        } catch (throwable: Throwable) {
            logger.error(throwable.message)

            throw UnexpectedErrorException(throwable)
        }
    }
}

interface UseCase<RESULT, PARAMS> {
    suspend fun execute(params: PARAMS?): RESULT
}
