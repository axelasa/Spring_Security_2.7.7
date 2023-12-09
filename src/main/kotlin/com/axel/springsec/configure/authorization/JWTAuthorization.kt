package com.axel.springsec.configure.authorization

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.AuthenticationEntryPoint

@Configuration
class JWTConfig {

    @Bean
    fun algorithm (@Value("var.jwt") jwtSecret: String) : Algorithm   {
        return Algorithm.HMAC256(jwtSecret)
    }
}

class JWTAuthorization(private val username:String, private val authorities :MutableCollection<GrantedAuthority>,): Authentication {
    override fun getName(): String {
       return username
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getDetails(): Any? {
        return  null
    }

    override fun getPrincipal(): Any {
        return username
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {

    }

    override fun toString(): String {
        return "JWTAuthorization(username='$username', authorities=${authorities.map { it.authority }.toList()})"
    }


}

