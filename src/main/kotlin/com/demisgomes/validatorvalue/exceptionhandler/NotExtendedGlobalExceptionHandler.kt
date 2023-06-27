package com.demisgomes.validatorvalue.exceptionhandler

import com.demisgomes.validatorvalue.exception.AuthenticationException
import com.demisgomes.validatorvalue.exception.InvalidCredentialsException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

//This handler is deactivated. To use it, uncomment the next line and comment other ControllerAdvice annotations.
//@ControllerAdvice
class NotExtendedGlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException::class)
     fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<Any> {

        val errors = mutableListOf<String>()
        ex.bindingResult.fieldErrors.forEach { fieldError ->
            errors.add("${fieldError.field}: ${fieldError.defaultMessage}")
        }

        return ResponseEntity(
                ApiError("Error on validation", errors),
                HttpHeaders(),
                HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(InvalidCredentialsException::class, AuthenticationException::class)
    fun handleUnauthorizedException(ex: Exception, request: WebRequest): ResponseEntity<Any> {

        return ResponseEntity(
                ApiError("Access denied: ${ex.localizedMessage}"),
                HttpHeaders(),
                HttpStatus.UNAUTHORIZED
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception, request: WebRequest): ResponseEntity<Any> {

        return ResponseEntity(
                ApiError("An internal server error occurred: ${ex.localizedMessage}"),
                HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }
}