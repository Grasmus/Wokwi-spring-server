package com.wokwi_spring.data.constants

const val MQTT_CLIENT_SEND_ID = "springServerSensorData"
const val MQTT_CLIENT_RECEIVE_ID = "springServerCommand"
const val MQTT_RECEIVE_TOPIC = "iotProjectTPPKM/sensorTopic"
const val MQTT_SEND_TOPIC = "iotProjectTPPKM/actuatorTopic"

enum class SensorType {
    Pir, Humidity, Temperature
}
