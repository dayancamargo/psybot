package com.psybot.exception

import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.resource.NoResourceFoundException
import org.springframework.web.server.MethodNotAllowedException
import org.springframework.web.server.ServerWebInputException


@ControllerAdvice
class ExceptionHandler(val messageSource: MessageSource) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(exception: NoResourceFoundException): ResponseEntity<String?> {
        return ResponseEntity.status(exception.body.status).body(exception.reason)
    }

    @ExceptionHandler(MethodNotAllowedException::class)
    fun handleMethodNotAllowedException(exception: MethodNotAllowedException): ResponseEntity<String?> {
        return ResponseEntity.status(exception.body.status).body(exception.body.detail)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any?> {
        val errors: MutableMap<String, String?> = HashMap()
        exception.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            if (errors.containsKey(fieldName)) {
                errors[fieldName] = errors[fieldName].plus("; ").plus(errorMessage)
            } else {
                errors[fieldName] = errorMessage
            }
        }
        return ResponseEntity.status(exception.body.status).body(" Errors %s ".format(errors))
    }

    @ExceptionHandler(ServerWebInputException::class)
    fun handleServerWebInputException(exception: ServerWebInputException): ResponseEntity<String?> {
        if (exception is WebExchangeBindException) {
            val errors: MutableMap<String, String?> = HashMap()
            exception.bindingResult.allErrors.forEach { error ->
                val fieldName = (error as FieldError).field
                val errorMessage = error.getDefaultMessage()
                if (errors.containsKey(fieldName)) {
                    errors[fieldName] = errors[fieldName].plus("; ").plus(errorMessage)
                } else {
                    errors[fieldName] = errorMessage
                }
            }
            return ResponseEntity.status(exception.body.status).body(errors.toString())
        } else {
            return ResponseEntity.status(exception.body.status).body(" Error %s On %s".format(exception.body.detail, exception.methodParameter?.parameter?.name))
        }
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<String> {
        logger.error("Error: {}", exception)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.message)
    }
}