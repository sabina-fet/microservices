package com.example.sabina.api.kafka

import com.example.sabina.api.models.Transaction
import com.example.sabina.api.service.KafkaTransactionMessage
import com.google.gson.Gson
import jakarta.annotation.PreDestroy
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaTransactionService(
    @Value("\${spring.kafka.producer.transaction-topic}")
    private val topicName: String,
    kafka: KafkaTemplate<String, String>
) {

    companion object {
        private val gson = Gson()
        private const val queueSize = 1000
    }


    private val publisher = KafkaProxyPublisher(kafka, topicName, queueSize)

    fun send(msg: KafkaTransactionMessage) =
        publisher.offer(
            ProducerRecord(
                topicName,
                gson.toJson(msg)
            )
        ).also {
            println("Transaction offered: $it")
        }

    @PreDestroy
    fun stop() = publisher.stop()

    fun queueSize() = publisher.queueSize()
}