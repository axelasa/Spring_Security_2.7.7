package com.axel.springsec.configure.filters

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.axel.springsec.configure.authorization.JWTAuthorization
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
class AuthorizationFilter(val algorithm:Algorithm): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authValue = request.getHeader(HttpHeaders.AUTHORIZATION)

       val token:String = authValue.replace("Bearer ","")

       val jwtDecode:DecodedJWT = JWT.require(algorithm).build().verify(token)
       val username:String = jwtDecode.subject

        val authorities:MutableList<GrantedAuthority> = mutableListOf()

        val role:String = jwtDecode.getClaim("role").asString()

        authorities.add(GrantedAuthority{"ROLE_$role"})

        val authorization = JWTAuthorization(username = username, authorities = authorities )


        SecurityContextHolder.getContext().authentication = authorization

        filterChain.doFilter(request,response)

    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val authorizationHeader =request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorizationHeader.isNullOrEmpty()) return true

        if (!authorizationHeader.startsWith("Bearer")) return true

        return false


    }
}