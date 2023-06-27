package com.demisgomes.validatorvalue.controller

import com.demisgomes.validatorvalue.domain.User
import com.demisgomes.validatorvalue.domain.UserLogin
import com.demisgomes.validatorvalue.exception.InvalidCredentialsException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
class LoginController {

    @PostMapping("/login")
    fun login(@RequestBody userLogin: UserLogin): User {
        if (userLogin.email == "email@mail.com" && userLogin.password == "12345"){
            return User("User", "email@mail.com", "12345", 5.0, 0)
        }

        throw RuntimeException()
        throw InvalidCredentialsException()
    }

}