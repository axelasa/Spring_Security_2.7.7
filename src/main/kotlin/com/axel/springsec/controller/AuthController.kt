package com.axel.springsec.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("auth")
class AuthController {
    @GetMapping("user")
    @PreAuthorize("hasAnyRole('user','admin')")//method security
    fun userAuthentication(principal: Principal):ResponseEntity<Any>{
        val detail:String = principal.toString()

        return ResponseEntity.ok().body(detail)
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('admin')")
    fun adminAuthentication(principal: Principal):ResponseEntity<Any>{
        val detail:String = principal.toString()

        return ResponseEntity.ok().body(detail)
    }


}