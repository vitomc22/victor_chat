package com.chat.victor_chat.repository


import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


import java.time.Instant

@Table(name = "MESSAGES")
@Entity
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
