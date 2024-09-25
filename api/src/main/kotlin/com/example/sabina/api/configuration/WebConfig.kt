package com.example.sabina.api.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author Sabina Muhic
 */
@Configuration
class WebConfig {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3001")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                    .allowedHeaders("*")
                    .allowedHeaders("Authorization", "Content-Type", "Accept")
                    .allowCredentials(true)
            }
        }
    }
}