package com.melalex.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class ChatApplication

fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}
