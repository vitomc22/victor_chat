package com.chat.victor_chat

import com.chat.victor_chat.repository.Message
import com.chat.victor_chat.service.MessageVM
import java.time.temporal.ChronoUnit

fun MessageVM.prepareForTesting() = copy(id = null, sent = sent.truncatedTo(ChronoUnit.MILLIS))

fun Message.prepareForTesting() = copy(id = null, sent = sent.truncatedTo(ChronoUnit.MILLIS))