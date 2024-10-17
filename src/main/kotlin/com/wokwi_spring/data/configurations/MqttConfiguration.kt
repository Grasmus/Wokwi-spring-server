package com.wokwi_spring.data.configurations

import com.wokwi_spring.data.constants.*
import com.wokwi_spring.data.mqtt.MqttMessageHandler
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler

@Configuration
@ComponentScan(basePackageClasses = [MqttMessageHandler::class])
@EnableAutoConfiguration
class MqttConfiguration {

    @Autowired
    lateinit var mqttMessageHandler: MqttMessageHandler

    @Value("\${mqtt.url}")
    lateinit var mqttUrl: String

    @Value("\${mqtt.username}")
    lateinit var mqttUsername: String

    @Value("\${mqtt.password}")
    lateinit var mqttPassword: String

    @Bean
    fun mqttOutboundFactory(): MqttPahoClientFactory {
        val options = MqttConnectOptions().apply {
            serverURIs = arrayOf(mqttUrl)
            userName = mqttUsername
            password = mqttPassword.toCharArray()
        }

        return DefaultMqttPahoClientFactory().apply {
            connectionOptions = options
        }
    }

    @Bean
    fun mqttInboundFactory(): MqttPahoClientFactory {
        val options = MqttConnectOptions().apply {
            serverURIs = arrayOf(mqttUrl)
            userName = mqttUsername
            password = mqttPassword.toCharArray()
        }

        return DefaultMqttPahoClientFactory().apply {
            connectionOptions = options
        }
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    fun mqttOutbound() =
        MqttPahoMessageHandler(MQTT_CLIENT_SEND_ID, mqttOutboundFactory()).apply {
            setAsync(true)
            setDefaultTopic(MQTT_SEND_TOPIC)
        }

    @Bean
    fun mqttInbound(): StandardIntegrationFlow = IntegrationFlow.from(
        MqttPahoMessageDrivenChannelAdapter(
            MQTT_CLIENT_RECEIVE_ID,
            mqttInboundFactory(),
            MQTT_RECEIVE_TOPIC
        )
    ).handle(
        mqttMessageHandler
    ).get()

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    interface MqttGateway {
        fun sendToMqtt(data: String?)
    }
}
