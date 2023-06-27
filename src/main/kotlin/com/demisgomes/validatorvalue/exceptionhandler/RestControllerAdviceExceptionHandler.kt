package com.demisgomes.validatorvalue.exceptionhandler

import com.demisgomes.validatorvalue.exception.AuthenticationException
import com.demisgomes.validatorvalue.exception.InvalidCredentialsException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


//This handler is deactivated. To use it, uncomment the next line and comment other ControllerAdvice annotations.
//@RestControllerAdvice
class RestControllerAdviceExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
     fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException): ApiError {

        val errors = mutableListOf<String>()
        ex.bindingResult.fieldErrors.forEach { fieldError ->
            errors.add("${fieldError.field}: ${fieldError.defaultMessage}")
        }

        return ApiError("Error on validation", errors)
    }

    @ExceptionHandler(InvalidCredentialsException::class, AuthenticationException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnauthorizedException(ex: Exception): ApiError {

        return ApiError("Access denied: ${ex.localizedMessage}")
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(ex: Exception): ApiError {

        return ApiError("An internal server error occurred: ${ex.localizedMessage}")
    }

}