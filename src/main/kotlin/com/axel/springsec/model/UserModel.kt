package com.axel.springsec.model

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotBlank

data class UserModel (
    @field:NotBlank(message = "This field can not be NUll") val username:String,
    @field:NotNull(value = "This field can not be NUll") val roleId:Int
)