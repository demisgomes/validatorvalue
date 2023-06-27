package com.demisgomes.validatorvalue.controller

import com.demisgomes.validatorvalue.domain.Dashboard
import com.demisgomes.validatorvalue.domain.User
import com.demisgomes.validatorvalue.exception.AuthenticationException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController {

    @PostMapping("/users")
    fun createUser(@Valid @RequestBody user: User): User {
        return user
    }

    @GetMapping("/user/dashboard")
    fun getDashboard(): Dashboard {
        if (isAuthenticated()){
            return Dashboard()
        }
        throw AuthenticationException()
    }

    private fun isAuthenticated() = false
}