package com.wokwi_spring.domain.configurations

import com.wokwi_spring.domain.usecases.GetLastSensorDataUseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [GetLastSensorDataUseCase::class])
class DomainConfiguration
