package com.chat.victor_chat.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface MessageRepository : CrudRepository<Message, String> {
    @Query(value = "SELECT * FROM MESSAGES ORDER BY SENT", nativeQuery = true)
    fun findLatest(): List<Message>


    @Query(value = "SELECT * FROM MESSAGES WHERE  ID = ?1 ORDER BY SENT", nativeQuery = true)
    fun findLatest(@Param("id") id: String): List<Message>

}