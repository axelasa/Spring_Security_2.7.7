package com.axel.springsec.configure.basic

import com.axel.springsec.configure.service.TokenService
import com.axel.springsec.model.ApiResponse
import com.axel.springsec.model.TokenResponse
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class BasicConfig{


    @Bean
    fun authenticationSuccessHandler(objectMapper: ObjectMapper, tokenService:TokenService): AuthenticationSuccessHandler {

        return AuthenticationSuccessHandler { request, response, authentication ->
            //token
            val token: TokenResponse = tokenService.createToken(authentication.name,authentication.details.toString())

            val result:ApiResponse = ApiResponse(HttpStatus.OK.value(),"Good Stuff",token)

            response.contentType = MediaType.APPLICATION_JSON_VALUE

            objectMapper.writeValue(response.outputStream,result)
            //return response

        }
    }

    @Bean
    fun authenticationFailureHandler(objMapper: ObjectMapper):AuthenticationFailureHandler{

        return AuthenticationFailureHandler { request, response, exception ->

            val failure:ApiResponse = ApiResponse(HttpStatus.UNAUTHORIZED.value(),exception.message,"Sorry The Mobile Subscriber Cannot be Reached")
            objMapper.writeValue(response.outputStream,failure)
        }
    }

    @Bean
    fun authenticationEntryPoint(objectMapper: ObjectMapper): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { request: HttpServletRequest?, response: HttpServletResponse, ex: AuthenticationException ->
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            val apiResponse: ApiResponse = ApiResponse(HttpStatus.UNAUTHORIZED.value(),ex.message,null)

            objectMapper.writeValue(response.outputStream, apiResponse)
        }
    }

    @Bean
    fun accessDeniedHandler(objectMapper: ObjectMapper):AccessDeniedHandler{
        return AccessDeniedHandler { request: HttpServletRequest?, response: HttpServletResponse, ex: AccessDeniedException ->
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            val apiResponse: ApiResponse = ApiResponse(HttpStatus.UNAUTHORIZED.value(),ex.message,null)

            objectMapper.writeValue(response.outputStream, apiResponse)
        }
    }
}