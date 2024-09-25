package com.example.sabina.api.aop

import com.example.sabina.api.configuration.ReadWriteRoutingDataSource
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

/**
 * @author Sabina Muhic
 */
@Aspect
@Component
class DataSourceRoutingAspect {

    @Before("execution(* com.example.sabina.api.repositories.*.find*(..))")
    fun useReadDataSource() {
        ReadWriteRoutingDataSource.setDataSourceType("read")
    }

    @Before("execution(* com.example.sabina.api.repositories.*.save*(..)) || execution(* com.example.sabina.api.repositories.*.update*(..))")
    fun useWriteDataSource() {
        ReadWriteRoutingDataSource.setDataSourceType("write")
    }

    @Before("execution(* com.example.sabina.api.repositories.*.*(..))")
    fun clearDataSourceType() {
        ReadWriteRoutingDataSource.clear()
    }
}