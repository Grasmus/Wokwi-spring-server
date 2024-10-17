package com.wokwi_spring.data.utils

interface BaseMapper<OUTPUT> {
    fun mapToDomain(): OUTPUT
}
