package net.robi42.giprocessor

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.handler.annotation.SendTo
import java.time.Instant

@EnableBinding(Processor::class)
class GreetingProcessor {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    fun process(greeting: Greeting) =
            greeting.copy(text = greeting.text.replace("Hello world", "Hello Spring Cloud Stream world"))

}

data class Greeting(
        val text: String,
        val timestamp: Instant
)
