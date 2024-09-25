package com.example.sabina.api.kafka

import mu.KotlinLogging.logger
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicLong

class KafkaProxyPublisher(
    private val kafka: KafkaTemplate<String, String>,
    private val topicName: String,
    kafkaQueueSize: Int
) {
    private val queue = LinkedBlockingQueue<ProducerRecord<String, String>>(kafkaQueueSize)
    private val worker = Worker(1)

    companion object {
        private val log = logger {}
        private val retriesCounter = AtomicLong()
        private val failedCounter = AtomicLong()
        private val rejectedCounter = AtomicLong()
    }

    fun queueSize() = queue.size

    fun offer(kafkaMessage: ProducerRecord<String, String>) {
        if (!queue.offer(kafkaMessage)) {
            log.error { "Rejected message to Kafka event queue" }
            rejectedCounter.incrementAndGet()
        }
    }

    private inner class Worker(private val workerId: Int) {
        val thread = Thread({ loop() }, "Worker-$workerId").apply {
            uncaughtExceptionHandler =
                Thread.UncaughtExceptionHandler { _, ex -> log.error(ex) { "Unhandled exception $ex" } }
            start()
        }

        fun loop() {
            log.info { "Started worker $workerId." }

            while (!Thread.interrupted()) {
                try {
                    val msg = queue.take()
                    println("Sending message $msg")

                    kafka.send(msg).whenComplete { res, err ->
                        when (err) {
                            null -> log.info { "Transaction sent: $res" }
                            else -> {
                                log.error(err) { "Transaction failed" }
                                queue.offer(msg)
                            }
                        }
                    }
                } catch (ex: InterruptedException) {
                    break
                } catch (ex: Exception) {
                    log.error(ex) { "Exception in Kafka Proxy worker: ${ex.stackTraceToString()}" }
                }
            }

            log.info { "Worker $workerId stopped." }
        }
    }

    fun stop() {
        log.info { "Stopping Kafka event processing queue consumer $topicName." }
        worker.thread.interrupt()
        worker.thread.join()
        log.info { "Stopped Kafka event processing queue consumer $topicName." }
    }

//    fun collect() = listOf(
//        Collector.MetricFamilySamples(
//            "kafka_queue_size",
//            Collector.Type.GAUGE,
//            "Size of Queue towards Kafka Proxy",
//            listOf(
//                Collector.MetricFamilySamples.Sample(
//                    "kafka_queue_size",
//                    listOf("publisher"),
//                    listOf(publisherName),
//                    queue.size.toDouble()
//                )
//            )
//        ),
//        Collector.MetricFamilySamples(
//            "kafka_retries_counter",
//            Collector.Type.GAUGE,
//            "Kafka retry count",
//            listOf(
//                Collector.MetricFamilySamples.Sample(
//                    "kafka_retries_counter",
//                    listOf("publisher"),
//                    listOf(publisherName),
//                    retriesCounter.get().toDouble()
//                )
//            )
//        ),
//        Collector.MetricFamilySamples(
//            "kafka_reject_counter",
//            Collector.Type.GAUGE,
//            "Kafka reject count",
//            listOf(
//                Collector.MetricFamilySamples.Sample(
//                    "kafka_reject_counter",
//                    listOf("publisher"),
//                    listOf(publisherName),
//                    rejectedCounter.get().toDouble()
//                )
//            )
//        ),
//        Collector.MetricFamilySamples(
//            "kafka_failed_counter",
//            Collector.Type.GAUGE,
//            "Kafka failed count",
//            listOf(
//                Collector.MetricFamilySamples.Sample(
//                    "kafka_failed_counter",
//                    listOf("publisher"),
//                    listOf(publisherName),
//                    failedCounter.get().toDouble()
//                )
//            )
//        ),
//    )
}
