package com.wokwi_spring.data.configurations

import com.wokwi_spring.data.repositories.SensorRepositoryImpl
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(MqttConfiguration::class)
@ComponentScan(basePackageClasses = [SensorRepositoryImpl::class])
class DataConfiguration
