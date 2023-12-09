package com.axel.springsec.model

import javax.validation.constraints.NotBlank

data class RoleModel (
    @field:NotBlank(message = "This field cannot be empty") val name:String
)