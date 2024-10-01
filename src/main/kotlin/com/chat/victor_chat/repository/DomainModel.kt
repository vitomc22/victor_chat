package com.chat.victor_chat.repository

import jakarta.persistence.*
import java.time.Instant

@Table(name = "MESSAGES")
@Entity
data class Message(
    val content: String,
    val contentType: ContentType,
    val sent: Instant,
    val userName: String,
    val userAvatarImageLink: String,
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: String? = null
)

enum class ContentType {
    PLAIN, MARKDOWN
}
