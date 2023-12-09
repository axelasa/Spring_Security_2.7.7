package com.axel.springsec.repository

import com.axel.springsec.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RoleRepository:JpaRepository<RoleEntity,Int> {
    fun findByNameIgnoreCase(name:String):Optional<RoleEntity>
}