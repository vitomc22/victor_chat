package com.chat.victor_chat.service

import com.chat.victor_chat.repository.ContentType
import com.chat.victor_chat.repository.Message
import com.chat.victor_chat.repository.MessageRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.net.URL

@Service
@Primary
class PersistentMessageService(val messageRepository: MessageRepository) : MessageService {

    override fun latest(): List<MessageVM> = messageRepository.findLatest().map {
        with(it) {
            MessageVM(
                content, UserVM(
                    userName, URL(userAvatarImageLink)
                ), sent, id
            )
        }
    }

    override fun after(lastMessageId: String): List<MessageVM> = messageRepository.findLatest(lastMessageId).map {
        with(it) {
            MessageVM(
                content, UserVM(
                    userName, URL(userAvatarImageLink)
                ), sent, id
            )
        }
    }

    override fun post(message: MessageVM) {
        messageRepository.save(with(message) {
            Message(
                content, ContentType.PLAIN, sent, user.name, user.avatarImageLink.toString()
            )
        })
    }
}