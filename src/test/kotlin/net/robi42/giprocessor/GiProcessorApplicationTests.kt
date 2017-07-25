package net.robi42.giprocessor

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.messaging.support.GenericMessage
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant.now
import javax.inject.Inject

@ActiveProfiles("test")
@RunWith(SpringRunner::class)
@Suppress("SpringKotlinAutowiring")
@SpringBootTest class GiProcessorApplicationTests {

    @Inject private lateinit var processor: Processor

    @Inject private lateinit var messageCollector: MessageCollector
    @Inject private lateinit var objectMapper: ObjectMapper

    @Test fun `context loads`() {}

    @Test fun `sends greeting message`() {
        val sentMessage = GenericMessage(Greeting(text = "Hi", timestamp = now()))
        processor.output().send(sentMessage)

        val receivedMessage = messageCollector
                .forChannel(processor.output())
                .poll()

        assertThat(receivedMessage.payload)
                .isEqualTo(sentMessage.toJson())
    }

    private fun GenericMessage<Greeting>.toJson()
            = objectMapper.writeValueAsString(this.payload)

}
