package com.techtest

import com.techtest.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureKoin()
    configureRouting()
    configureExceptions()
}