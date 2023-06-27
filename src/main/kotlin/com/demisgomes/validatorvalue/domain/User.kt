package com.demisgomes.validatorvalue.domain

import javax.validation.constraints.*

data class User(
    @field:NotBlank(message = "{validation.name.message}")
    val name: String,
    @field:Email(message = "the email '\${validatedValue}' not follows the pattern mail@mail.com")
    val email: String,
    @field:Size(min=3, max=20, message = "the password field must have more than {min} and less than {max} digits")
    val password: String,
    @field:Digits(integer = 2, fraction = 1, message = "numeric value out of bounds (<{integer} digits>.<{fraction} digit\${fraction > 1 ? 's' : ''}> expected)")
    val yearsOfExperience: Double,
    @field:Max(value=2097152, message = "the image size is \${formatter.format('%1$.2f', validatedValue/1048576)} MB. It should be less than \${value/1048576} MB")
    val imageBytes: Int,
    )

