package com.chat.victor_chat.service

import com.chat.victor_chat.asViewModel
import com.chat.victor_chat.mapToViewModel
import com.chat.victor_chat.repository.ContentType
import com.chat.victor_chat.repository.Message
import com.chat.victor_chat.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
abstract class PersistentMessageService(val messageRepository: MessageRepository) : MessageService {

    val sender: MutableSharedFlow<MessageVM> = MutableSharedFlow()

    override suspend fun latest(): Flow<MessageVM> = messageRepository.findLatest().mapToViewModel()

    override suspend fun after(messageId: String): Flow<MessageVM> = messageRepository.findLatest(messageId).map {it.asViewModel()}

    override suspend fun post(message: Flow<MessageVM>) {
        messageRepository.save(with(message.first()) {
            Message(
                content, ContentType.MARKDOWN, sent, user.name, user.avatarImageLink.toString()
            )
        })
    }
}