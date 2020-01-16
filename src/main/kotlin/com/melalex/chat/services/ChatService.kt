package com.melalex.chat.services

import com.melalex.chat.domain.ChatMessage
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ChatService {

    fun send(chatMessage: ChatMessage): Mono<Unit>

    fun receive(user: String, room: String): Flux<ChatMessage>
}