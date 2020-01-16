package com.melalex.chat.services.impl

import com.melalex.chat.config.properties.KafkaProperties
import com.melalex.chat.domain.ChatMessage
import com.melalex.chat.kafka.KafkaToFluxAdapter
import com.melalex.chat.kafka.LoggingCallback
import com.melalex.chat.services.ChatService
import com.melalex.chat.services.UserService
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ChatServiceImpl(
        private val userService: UserService,
        private val kafkaTemplate: KafkaTemplate<String, ChatMessage>,
        private val kafkaProperties: KafkaProperties,
        private val loggingCallback: LoggingCallback,
        private val kafkaToFluxAdapter: KafkaToFluxAdapter
) : ChatService {

    override fun send(chatMessage: ChatMessage): Mono<Unit> {
        kafkaTemplate.send(kafkaProperties.topic, chatMessage.room, chatMessage)
                .addCallback(loggingCallback)

        return Mono.empty()
    }

    override fun receive(user: String, room: String): Flux<ChatMessage> =
            userService.isUserConnected(user).flatMapMany { isPresent ->
                if (isPresent) {
                    userService.addUser(user)
                    kafkaToFluxAdapter.asFlux().filter { it.room == room }
                } else Flux.error()
            }
}