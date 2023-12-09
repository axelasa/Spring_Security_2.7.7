package com.axel.springsec.model

import lombok.Builder

@Builder
data class ApiResponse(
    val status:Int,
    val description:String?,
    val data:Any?
)