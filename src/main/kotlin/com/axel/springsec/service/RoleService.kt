package com.axel.springsec.service

import com.axel.springsec.entity.RoleEntity
import com.axel.springsec.model.RoleModel
import java.util.Optional

interface RoleService {
     fun crateRole(role: RoleModel):RoleEntity
     fun findRoleByName(name:String):Optional<RoleEntity>
     fun findRoleById(id:Int):Optional<RoleEntity>
}