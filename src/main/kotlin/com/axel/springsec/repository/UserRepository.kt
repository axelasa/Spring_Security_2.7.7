package com.axel.springsec.repository

import com.axel.springsec.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository:JpaRepository<UserEntity,Int> {
    fun findByUsername(username:String):Optional<UserEntity>
}