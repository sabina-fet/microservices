package com.example.sabina.api.configuration

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

/**
 * @author Sabina Muhic
 */
class ReadWriteRoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any {
        return contextHolder.get()
    }

    companion object {
        private val contextHolder = ThreadLocal<String>()

        fun setDataSourceType(dataSourceType: String) {
            contextHolder.set(dataSourceType)
        }

        fun clear() {
            contextHolder.remove()
        }
    }
}