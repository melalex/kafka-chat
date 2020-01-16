package com.melalex.chat.domain

import java.util.*

data class ChatMessage(
        val message: String,
        val sender: String,
        val room: String,
        val date: Date
)