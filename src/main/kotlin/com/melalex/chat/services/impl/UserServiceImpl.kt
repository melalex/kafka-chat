package com.melalex.chat.services.impl

import com.melalex.chat.services.UserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class UserServiceImpl : UserService {

    private val users = ConcurrentHashMap.newKeySet<String>()

    override fun isUserConnected(user: String): Mono<Boolean> = users.contains(user).toMono()

    override fun addUser(user: String): Mono<Unit> {
        users.add(user)

        return Mono.empty()
    }

    override fun removeUser(user: String): Mono<Unit> {
        users.remove(user)

        return Mono.empty()
    }
}