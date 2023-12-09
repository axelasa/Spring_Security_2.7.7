package com.axel.springsec.configure.authentication

import com.axel.springsec.exception.AuthExceptions
import com.axel.springsec.model.ApiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//BasicAuthentication is => The object that gets passed by authentication manager to authenticate a user
class BasicAuthentication(private val username:String, private var authe:Boolean, private val password:String,  var roleName:String?):Authentication{

    override fun getName(): String {
       return username
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableSetOf()
    }

    override fun getCredentials(): Any {
        return password
    }

    override fun getDetails(): Any? {
        return roleName
    }

    override fun getPrincipal(): Any {
        return username
    }

    override fun isAuthenticated(): Boolean {
       return authe
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authe = isAuthenticated
    }

    override fun toString(): String {
        return "BasicAuthentication(username='$username', authe=$authe, password='$password')"
    }

}

@Configuration
class BasicAuthenticationProvider(var userDetailsService:UserDetailsService): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        //here get supported authentication

        val auth:BasicAuthentication = authentication as BasicAuthentication

        //validate the authentication

         val username:String = auth.name
         val userDetails:UserDetails = userDetailsService.loadUserByUsername(username)

        //verify password (stored password)
         val storedPassword = userDetails.password
        //password attempt
        val passAttempt =  auth.credentials

        val validPass:Boolean = passAttempt == storedPassword
        print("=========== $username:$storedPassword => $validPass ==============")
        if (!validPass) throw AuthExceptions("Invalid password")

        //return authenticated authentication
        auth.roleName = userDetails.authorities.stream().findFirst().map {
            it.authority
        }.orElse(null)
        auth.isAuthenticated = true

        return auth

    }

    override fun supports(authentication: Class<*>?): Boolean {
     return authentication == BasicAuthentication::class.java

    }
}


