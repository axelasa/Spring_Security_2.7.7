package com.axel.springsec.controller

import com.axel.springsec.model.RoleModel
import com.axel.springsec.service.RoleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("roles")
class RolesController(val roleService:RoleService) {
    @PostMapping
    fun createRoles( @RequestBody  role:RoleModel):ResponseEntity<Any>{
        val newRole = roleService.crateRole(role)
       return  ResponseEntity.ok().body(newRole)
    }
}