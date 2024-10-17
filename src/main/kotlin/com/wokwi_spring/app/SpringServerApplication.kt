package com.wokwi_spring.app

import com.wokwi_spring.data.configurations.DataConfiguration
import com.wokwi_spring.domain.configurations.DomainConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(DataConfiguration::class, DomainConfiguration::class)
@EnableAutoConfiguration
class SpringServerApplication

fun main(args: Array<String>) {
	runApplication<SpringServerApplication>(*args)
}
