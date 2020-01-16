package com.melalex.chat.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka")
data class KafkaProperties(
        var url: String,
        var topic: String,
        var partitionCount: Int,
        var replicaFactor: Short
)