package com.axel.springsec.configure.filters

import com.axel.springsec.configure.authentication.BasicAuthentication
import com.axel.springsec.exception.AuthExceptions
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class BasicFilter( authManager: AuthenticationManager,  authenticationHandler: AuthenticationSuccessHandler, authFailureHandler: AuthenticationFailureHandler): UsernamePasswordAuthenticationFilter() {

    init {
        authenticationManager = authManager

        setAuthenticationSuccessHandler(authenticationHandler)

        setAuthenticationFailureHandler(authFailureHandler)
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        //obtain username and password from the request
        val authHeader:String? = request!!.getHeader("Authorization")

        if(authHeader.isNullOrEmpty()) throw AuthExceptions("Missing Authorization Header")
        val basicPrefix = "Basic "
         if (!authHeader.startsWith(basicPrefix)) throw AuthExceptions("Invalid Authorization Header")

        val basicToken = authHeader.replace(basicPrefix,"")

        val tokenDecode = String(Base64.getDecoder().decode(basicToken))



        val keyPair:List<String> = tokenDecode.split(":")

        val username = keyPair[0]
        val password = keyPair[1]


        //create a basic authentication with the username and password
        val auth:BasicAuthentication = BasicAuthentication(username,false, password,null)

        //with the authentication manager authenticate the basic authentication
          val authenticate:Authentication = authenticationManager.authenticate(auth)

        //return the result of the authentication from authentication manager
        return authenticate

    }

    override fun requiresAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Boolean {
        val path = request!!.servletPath
        return path.equals("/signIn")
    }
}