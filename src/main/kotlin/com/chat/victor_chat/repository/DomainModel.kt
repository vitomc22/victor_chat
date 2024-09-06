package com.chat.victor_chat.repository


import jakarta.persistence.Table
import org.springframework.data.annotation.Id

import java.time.Instant

@Table(name = "MESSAGES")
data class Message(
    val content: String,
    val contentType: ContentType,
    val sent: Instant,
    val userName: String,
    val userAvatarImageLink: String,
    @Id var id: String? = null
)

enum class ContentType {
    PLAIN
}
