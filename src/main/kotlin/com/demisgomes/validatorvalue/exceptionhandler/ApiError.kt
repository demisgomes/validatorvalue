package com.demisgomes.validatorvalue.exceptionhandler

data class ApiError(val message: String, val errors: List<String> = emptyList())