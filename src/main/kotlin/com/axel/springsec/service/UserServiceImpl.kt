package com.axel.springsec.service

import com.axel.springsec.entity.UserEntity
import com.axel.springsec.model.UserModel
import com.axel.springsec.repository.UserRepository
import javax.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class UserServiceImpl(val userRepo:UserRepository, val roleService:RoleService) : UserService {
    override fun createUser(user:UserModel): UserEntity {
        val existingUser = findByUsername(user.username)
        if (existingUser.isPresent) throw RuntimeException("This User Already Exists")

        val role = roleService.findRoleById(user.roleId).orElseThrow {
            RuntimeException("Role Not Found")
        }

        val newUser = UserEntity.createNew(user.username, role)
        return userRepo.save(newUser)
    }

    override fun findByUsername(username: String): Optional<UserEntity> {
        return userRepo.findByUsername(username)

    }
}