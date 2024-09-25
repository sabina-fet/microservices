package com.example.sabina.api.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Value("\${spring.datasource.write.url}")
    lateinit var writeDbUrl: String

    @Value("\${spring.datasource.write.username}")
    lateinit var writeDbUsername: String

    @Value("\${spring.datasource.write.password}")
    lateinit var writeDbPassword: String

    @Value("\${spring.datasource.read.url}")
    lateinit var readDbUrl: String

    @Value("\${spring.datasource.read.username}")
    lateinit var readDbUsername: String

    @Value("\${spring.datasource.read.password}")
    lateinit var readDbPassword: String

    @Bean(name = ["writeDataSource"])
    @Primary
    fun writeDataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(writeDbUrl)
            .username(writeDbUsername)
            .password(writeDbPassword)
            .build()
    }

    @Bean(name = ["readDataSource"])
    fun readDataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(readDbUrl)
            .username(readDbUsername)
            .password(readDbPassword)
            .build()
    }
}