package com.melalex.chat.kafka

import com.melalex.chat.domain.ChatMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import reactor.core.publisher.DirectProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxProcessor
import reactor.core.publisher.FluxSink
import java.util.function.Function


@Component
class KafkaToFluxAdapter {

    private val processor: FluxProcessor<ChatMessage, ChatMessage> = DirectProcessor.create<ChatMessage>().serialize()
    private val sink: FluxSink<ChatMessage> = processor.sink()

    @KafkaListener(topics = ["topicName"], groupId = "foo")
    fun listen(message: ChatMessage) {
        sink.next(message)
    }

    fun asFlux(): Flux<ChatMessage> = processor.map(Function.identity())
}