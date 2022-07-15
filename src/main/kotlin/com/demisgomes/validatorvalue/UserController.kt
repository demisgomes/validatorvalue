package com.demisgomes.validatorvalue

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
}