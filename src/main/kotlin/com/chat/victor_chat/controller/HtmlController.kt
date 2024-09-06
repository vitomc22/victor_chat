package com.chat.victor_chat.controller

import com.chat.victor_chat.service.MessageService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HtmlController(val messageService: MessageService) {

    @GetMapping("/")
    fun index(model: Model): String {
        val messages = messageService.latest()

        model["message"] = messages
        model["lastMessageId"] = messages.lastOrNull()?.id ?: ""
        return "chat"
    }
}