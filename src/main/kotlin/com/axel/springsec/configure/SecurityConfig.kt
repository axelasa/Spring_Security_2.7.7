package com.axel.springsec.configure

//import com.axel.springsec.configure.filters.BasicFilter
import com.axel.springsec.configure.filters.AuthorizationFilter
import com.axel.springsec.configure.filters.BasicFilter
import org.apache.catalina.core.ApplicationFilterChain
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity,
                            authEntryPoint: AuthenticationEntryPoint,
                            authenticationManager: AuthenticationManager,
                            basicFilter: BasicFilter,authorizationFilter: AuthorizationFilter):SecurityFilterChain{


        httpSecurity.csrf().disable()
            .httpBasic()
            .and()
            .addFilterBefore(basicFilter,UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(authorizationFilter,BasicAuthenticationFilter::class.java)
            .authenticationManager(authenticationManager)
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.mvcMatchers("/auth/**").authenticated()
            }

        return httpSecurity.build()
    }
}

