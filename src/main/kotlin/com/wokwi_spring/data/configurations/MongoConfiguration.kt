package com.wokwi_spring.data.configurations

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
@ComponentScan
class MongoConfiguration: AbstractMongoClientConfiguration() {

    @Value("\${database.connection_string}")
    lateinit var connection: String

    override fun getDatabaseName() = "SensorData"

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(connection)

        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()

        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(serverApi)
            .build()

        return MongoClients.create(mongoClientSettings)
    }
}
