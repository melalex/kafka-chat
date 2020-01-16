package com.melalex.chat.config

import com.melalex.chat.config.properties.KafkaProperties
import com.melalex.chat.domain.ChatMessage
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class KafkaConfig {

    @Autowired
    lateinit var kafkaProperties: KafkaProperties

    @Bean
    fun kafkaAdmin(): KafkaAdmin = KafkaAdmin(mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.url
    ))

    @Bean
    fun messagesTopic(): NewTopic =
            NewTopic(kafkaProperties.topic, kafkaProperties.partitionCount, kafkaProperties.replicaFactor)

    @Bean
    fun producerFactory(): DefaultKafkaProducerFactory<String, ChatMessage> = DefaultKafkaProducerFactory(mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.url,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
    ))

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, ChatMessage> = KafkaTemplate(producerFactory())
}