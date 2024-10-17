package com.wokwi_spring.data.constants

const val MQTT_CLIENT_SEND_ID = "springServerSensorData"
const val MQTT_CLIENT_RECEIVE_ID = "springServerCommand"
const val MQTT_RECEIVE_TOPIC = "sensorData"
const val MQTT_SEND_TOPIC = "command"

enum class SensorType {
    Pir, Humidity, Temperature
}
