package com.melalex.chat.services

import reactor.core.publisher.Mono

interface UserService {

    fun isUserConnected(user: String): Mono<Boolean>

    fun addUser(user: String): Mono<Unit>

    fun removeUser(user: String): Mono<Unit>
}