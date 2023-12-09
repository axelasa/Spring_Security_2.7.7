package com.axel.springsec.service

import com.axel.springsec.entity.UserEntity
import com.axel.springsec.model.UserModel
import java.util.*

interface UserService {
    fun createUser(user:UserModel):UserEntity
    fun findByUsername(username: String): Optional<UserEntity>
}