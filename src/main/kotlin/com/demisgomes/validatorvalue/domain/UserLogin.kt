package com.demisgomes.validatorvalue.domain

import javax.validation.constraints.*

data class UserLogin(val email: String, val password: String)

