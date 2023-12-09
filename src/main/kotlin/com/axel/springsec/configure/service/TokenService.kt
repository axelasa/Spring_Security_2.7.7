package com.axel.springsec.configure.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.axel.springsec.model.TokenResponse
import org.springframework.stereotype.Service
import java.util.Date
import java.util.concurrent.TimeUnit

@Service
class TokenService(val algorithm: Algorithm) {

    fun createJwt(username: String,roleName:String): String {
        val currentTime:Long = System.currentTimeMillis()
        val duration = TimeUnit.HOURS.toMillis(1)
        val expiry = Date(currentTime + duration)

        return JWT.create()
            .withSubject(username)
            .withClaim("role",roleName)
            .withExpiresAt(expiry)
            .sign(algorithm)
    }

    fun createToken( username:String,roleName:String):TokenResponse{
        val accessToken = createJwt(username,roleName)
        val refreshToken = createJwt(username,roleName)

        return TokenResponse(accessToken,refreshToken)
    }
}