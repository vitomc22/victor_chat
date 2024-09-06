import com.chat.victor_chat.service.MessageService
import com.chat.victor_chat.service.MessageVM
import com.chat.victor_chat.service.UserVM
import com.github.javafaker.Faker

import org.springframework.stereotype.Service
import java.net.URL
import java.time.Instant
import kotlin.random.Random

@Service
class fakeMessageService : MessageService {


    val users: Map<String, UserVM> = mapOf(
        "Shakespeare" to UserVM(
            "Shakespeare", URL("https://blog.12min.com/wp-content/uploads/2018/05/27d-William-Shakespeare.jpg")
        ), "ChuckNorris" to UserVM(
            "ChuckNorris",
            URL("https://images01.military.com/sites/default/files/styles/full/public/2021-04/chucknorris.jpeg.jpg")
        ), "Pokemon" to UserVM(
            "Pokemon",
            URL("https://e7.pngegg.com/pngimages/476/159/png-clipart-pokemon-pikachu-pikachu-pokemon-games-pokemon-thumbnail.png")
        )
    )

    val usersQuotes: Map<String, () -> String> = mapOf(
        "Shakespeare" to {Faker().shakespeare().hamletQuote().toString()},
        "ChuckNorris" to { Faker().chuckNorris().fact() },
        "Yoda" to { Faker().pokemon().name() })

    override fun latest(): List<MessageVM> {
        val count = Random.nextInt(1, 15)
        return (0..count).map {
            val user = users.values.random()
            val userQuote = usersQuotes.getValue(user.name).invoke()

            MessageVM(
                userQuote, user, Instant.now(), Random.nextBytes(10).toString()
            )
        }.toList()
    }

    override fun after(lastMessageId: String): List<MessageVM> {
        return latest()
    }

    override fun post(message: MessageVM) {
        TODO("Not yet implemented")
    }
}