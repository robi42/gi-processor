package net.robi42.giprocessor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

fun main(args: Array<String>) {
    SpringApplication.run(GiProcessorApplication::class.java, *args)
}

@SpringBootApplication
class GiProcessorApplication
