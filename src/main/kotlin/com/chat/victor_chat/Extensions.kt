package com.chat.victor_chat

import com.chat.victor_chat.repository.ContentType
import com.chat.victor_chat.repository.Message
import com.chat.victor_chat.service.MessageVM
import com.chat.victor_chat.service.UserVM
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.URL


fun MessageVM.asDomainObject(contentType: ContentType = ContentType.MARKDOWN): Message = Message(
    content, contentType, sent, user.name, user.avatarImageLink.toString(), id
)

fun Message.asViewModel(): MessageVM = MessageVM(
    contentType.render(content), UserVM(userName, URL(userAvatarImageLink)), sent, id
)
fun Flow<Message>.mapToViewModel(): Flow<MessageVM> = map {it.asViewModel()}

fun MessageVM.asRendered(contentType: ContentType = ContentType.MARKDOWN): MessageVM =
    this.copy(content = contentType.render(this.content))

fun ContentType.render(content: String): String = when (this) {
    ContentType.PLAIN -> content
    ContentType.MARKDOWN -> {
        val flavour = CommonMarkFlavourDescriptor()
        HtmlGenerator(content, MarkdownParser(flavour).buildMarkdownTreeFromString(content), flavour).generateHtml()
    }
}