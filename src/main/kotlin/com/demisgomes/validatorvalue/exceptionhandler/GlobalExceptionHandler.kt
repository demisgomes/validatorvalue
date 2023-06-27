package com.demisgomes.validatorvalue.exceptionhandler

import com.demisgomes.validatorvalue.exception.AuthenticationException
import com.demisgomes.validatorvalue.exception.InvalidCredentialsException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler(){


    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {

        val errors = mutableListOf<String>()
        ex.bindingResult.fieldErrors.forEach { fieldError ->
            errors.add("${fieldError.field}: ${fieldError.defaultMessage}")
        }

        return handleExceptionInternal(
                ex,
                ApiError("Error on validation", errors),
                headers,
                HttpStatus.BAD_REQUEST,
                request
                )
    }

    @ExceptionHandler(InvalidCredentialsException::class, AuthenticationException::class)
    fun handleUnauthorizedException(ex: Exception, request: WebRequest): ResponseEntity<Any> {

        return handleExceptionInternal(
                ex,
                ApiError("Access denied: ${ex.localizedMessage}"),
                HttpHeaders(),
                HttpStatus.UNAUTHORIZED,
                request
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception, request: WebRequest): ResponseEntity<Any> {

        return handleExceptionInternal(
                ex,
                ApiError("An internal server error occurred: ${ex.localizedMessage}"),
                HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        )
    }
}