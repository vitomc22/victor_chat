package com.chat.victor_chat.repository


import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param

interface MessageRepository : CoroutineCrudRepository<Message, String> {
    @Query(value = "SELECT * FROM MESSAGES ORDER BY SENT")
    suspend fun findLatest(): Flow<Message>


    @Query(value = "SELECT * FROM MESSAGES WHERE  ID = ?1 ORDER BY SENT")
    suspend fun findLatest(@Param("id") id: String): Flow<Message>

}