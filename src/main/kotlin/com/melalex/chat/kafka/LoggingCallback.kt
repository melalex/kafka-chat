package com.melalex.chat.kafka

import com.melalex.chat.domain.ChatMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback

@Component
class LoggingCallback : ListenableFutureCallback<SendResult<String, ChatMessage>> {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun onSuccess(result: SendResult<String, ChatMessage>?) {
        log.info("Successfully send message: {}", result)
    }

    override fun onFailure(ex: Throwable) {
        log.info("Failed to send message", ex)
    }
}