package com.axel.springsec.exception

import org.springframework.security.core.AuthenticationException

class AuthExceptions(msg: String?) : AuthenticationException(msg)