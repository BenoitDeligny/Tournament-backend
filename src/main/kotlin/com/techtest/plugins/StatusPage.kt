package com.techtest.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import java.lang.IllegalArgumentException

// TODO: This is just a basic. And i'm not sure it's a good place to do it.
fun Application.configureExceptions() {
    install(StatusPages) {
        exception<Throwable> { call, throwable ->
            when(throwable) {
                is NotFoundException -> {
                    call.respond(
                        HttpStatusCode.NotFound,
                        ExceptionResponse("${throwable.message}", HttpStatusCode.NotFound.value)
                    )
                }
                is IllegalArgumentException -> {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ExceptionResponse("${throwable.message}", HttpStatusCode.BadRequest.value)
                    )
                }
            }
        }
    }
}

@Serializable
data class ExceptionResponse(
    val message: String,
    val code: Int
)