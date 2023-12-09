package com.axel.springsec.configure.authentication

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import javax.security.auth.message.AuthException

@Configuration
class SpringSecAuthenticationManager(private val basicAuthProvider: BasicAuthenticationProvider): AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        if (authentication == null ) throw AuthException("Authentication Required")
         if (basicAuthProvider.supports(authentication.javaClass)) return basicAuthProvider.authenticate(authentication)
             return authentication
    }
}