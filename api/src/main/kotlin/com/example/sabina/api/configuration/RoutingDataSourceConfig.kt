package com.example.sabina.api.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * @author Sabina Muhic
 */
@Configuration
class RoutingDataSourceConfig {
    @Bean
    fun routingDataSource(
        @Qualifier("writeDataSource") writeDataSource: DataSource,
        @Qualifier("readDataSource") readDataSource: DataSource
    ): DataSource {
        val targetDataSources: MutableMap<Any, Any> = HashMap()
        targetDataSources["master"] = writeDataSource
        targetDataSources["replica"] = readDataSource

        val routingDataSource = ReadWriteRoutingDataSource()
        routingDataSource.setTargetDataSources(targetDataSources)
        routingDataSource.setDefaultTargetDataSource(writeDataSource)

        return routingDataSource
    }
}