package com.axel.springsec.configure.service

import com.axel.springsec.entity.UserEntity
import com.axel.springsec.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserEntityService(val userRepo: UserRepository):UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) throw UsernameNotFoundException("Username required")
        val existingUser:Optional<UserEntity> = userRepo.findByUsername(username)

        //throw RuntimeException()
        if (existingUser.isEmpty) throw UsernameNotFoundException("Username required")

        val userD:UserDetails = object : UserDetails{
            override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                //permissions of the user

                return  mutableSetOf(GrantedAuthority { existingUser.get().role.name })
            }

            override fun getPassword(): String {
                return ""
            }

            override fun getUsername(): String {

               return username

            }

            override fun isAccountNonExpired(): Boolean {
                return true
            }

            override fun isAccountNonLocked(): Boolean {
                return true
            }

            override fun isCredentialsNonExpired(): Boolean {
                return true
            }

            override fun isEnabled(): Boolean {
                return true
            }

        }
        return userD
    }
}