package com.axel.springsec.controller

import com.axel.springsec.model.UserModel
import com.axel.springsec.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(val userService:UserService) {

    @PostMapping
    fun createUser(@RequestBody user:UserModel):ResponseEntity<Any>{
        val newUser = userService.createUser(user )
        return ResponseEntity.ok().body(newUser)
    }

}