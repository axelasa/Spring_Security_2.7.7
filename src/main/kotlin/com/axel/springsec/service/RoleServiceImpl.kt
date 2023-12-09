package com.axel.springsec.service

import com.axel.springsec.entity.RoleEntity
import com.axel.springsec.model.RoleModel
import com.axel.springsec.repository.RoleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
@Service
@Transactional
class RoleServiceImpl(val roleRepo: RoleRepository) : RoleService {
    override fun crateRole(role: RoleModel): RoleEntity {
        val existingRole = findRoleByName(role.name)
        if (existingRole.isPresent) throw RuntimeException("This role already Exists")

        val newRole = RoleEntity.createRole(role)

        return roleRepo.save(newRole)
    }

    override fun findRoleByName(name: String): Optional<RoleEntity> {
       return roleRepo.findByNameIgnoreCase(name)
    }

    override fun findRoleById(id: Int): Optional<RoleEntity> {
        return roleRepo.findById(id)
    }
}