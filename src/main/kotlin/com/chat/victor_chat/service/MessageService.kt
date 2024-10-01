package com.chat.victor_chat.service

import kotlinx.coroutines.flow.Flow

interface MessageService {

    suspend fun latest(): Flow<MessageVM>

    suspend fun after(messageId: String): Flow<MessageVM>

    fun stream(): Flow<MessageVM>

    suspend fun post(message: Flow<MessageVM>)
}