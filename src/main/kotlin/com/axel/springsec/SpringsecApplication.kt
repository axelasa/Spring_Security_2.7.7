package com.axel.springsec

import org.apache.catalina.core.ApplicationFilterChain
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringsecApplication

fun main(args: Array<String>) {
    //ApplicationFilterChain
    runApplication<SpringsecApplication>(*args)
}
